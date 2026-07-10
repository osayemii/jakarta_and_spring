# social-media

Spring Boot API for **Posts, Interactions (likes/comments/shares/bookmarks), and Messaging**, backed by **MySQL + JPA**.
This is your half of the backend — Prince's Auth/Profile/Notification module is separate.

Structured to match `springTaxi`: `entity/`, `repository/`, `service/`, `controller/` packages, where each service is a thin pass-through to its repository (same shape as `RideController` → `RideService` → `Ride`/`RideRepository`). No DTOs, no response wrappers, no pagination — controllers return entities directly.

## One-time MySQL setup

1. Make sure MySQL is running locally (same setup you used for springTaxi).
2. Create the database:
   ```sql
   CREATE DATABASE social_media;
   ```
3. `src/main/resources/application.properties` is already pointed at it (`root` user, blank password, same as springTaxi's `application.properties`) — edit the username/password there if yours differ.
4. `spring.jpa.hibernate.ddl-auto=update` means tables get created/updated automatically from the entity classes on startup — no manual schema needed.

## Running it

```
mvn spring-boot:run
```
Or in Eclipse: right-click the project → Run As → Spring Boot App. No `server.port` is set, so it runs on the default `8080` — add `server.port=...` to `application.properties` if you need to run this alongside Prince's Auth service at the same time.

## Data model

- `Post` — authorId, content, likeCount, commentCount, shareCount, createdAt
- `PostLike` — postId, userId, createdAt (one row per like)
- `PostShare` — postId, userId, optional comment (quote-repost), createdAt
- `Comment` — postId, authorId, content, parentCommentId (null = top-level, set = reply), createdAt
- `Bookmark` — userId, postId, createdAt
- `Conversation` — group (boolean), title, participantIds (list of user ids, stored via `@ElementCollection` in a separate table Hibernate manages automatically), createdAt, lastMessageAt
- `Message` — conversationId, senderId, content, createdAt

## No auth wired in yet

Like before, there's no real login here — Prince's Auth module owns that. Every request takes `authorId`/`userId`/`senderId` directly as a `Long` in the request body or as a query param. When Prince's Auth is ready, this is where you'd add a shared filter to read the authenticated user instead of trusting the client.

## Real-time messaging (WebSocket)

MySQL itself has no built-in push, so instant delivery is handled separately via STOMP over WebSocket (`config/WebSocketConfig.java`) — the database and the "instant" part are two independent concerns. Flow:

1. Flutter connects once to `ws(s)://<host>:8080/ws` (SockJS fallback included).
2. Subscribes to `/topic/conversations/{conversationId}` for the chat screen that's open.
3. Sending still goes through the normal REST endpoint below (`POST /conversations/{id}/messages`) — `MessageController` saves it to MySQL *and* broadcasts it to that topic in the same call, so every subscribed participant gets it immediately, no polling.

## API overview

**Posts** (`/posts`): `POST` create, `GET` feed, `GET /{id}`, `GET /user/{authorId}`, `PUT /{id}` (send full post body, only content is applied), `DELETE /{id}`

**Likes** (`/posts/{postId}/likes`): `POST` `{"userId": 2}`, `DELETE ?userId=2`, `GET` list, `GET /me?userId=2`

**Comments** (`/posts/{postId}/comments`): `POST` `{"authorId": 2, "content": "...", "parentCommentId": null}`, `GET` list, `DELETE /{commentId}`

**Shares** (`/posts/{postId}/shares`): `POST` `{"userId": 2, "comment": "optional"}`, `DELETE ?userId=2`, `GET` list

**Bookmarks** (`/bookmarks`): `POST` `{"userId": 1, "postId": 5}`, `DELETE /{id}`, `GET ?userId=1`

**Messaging**:
- `POST /conversations` — `{"group": false, "participantIds": [1, 2]}` for 1:1, or `{"group": true, "title": "...", "participantIds": [1,2,3]}` for a group
- `GET /conversations?userId=1` — my conversations
- `GET /conversations/{id}` — one conversation
- `POST /conversations/{id}/messages` — `{"senderId": 1, "content": "..."}`
- `GET /conversations/{id}/messages` — full history (use this once when a chat screen opens; new messages after that arrive live over WebSocket)

## Not built here (Prince's side)

Authentication, user profiles, follow/unfollow, and notifications.
