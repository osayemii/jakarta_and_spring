<%@ page language="java" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
	<title>Upload Profile</title>

	<style>
		body {
			font-family: Arial, sans-serif;
			margin: 40px;
		}
		#progress {
			width: 400px;
			height: 40px;
			border: 2px solid black;
			margin-top: 20px;
		}
		#bar {
			height: 100%;
			width: 0%;
			background-color: green;
			text-align: center;
			color: white;
			line-height: 40px;
		}
		.loader {
			width: 14px;
			height: 14px;
			border: 2px solid #ccc;
			border-top: 2px solid #000;
			border-radius: 50%;
			display: inline-block;
			animation: spin 0.7s linear infinite;
			vertical-align: middle;
		}
		@keyframes spin {
			100% {
				transform: rotate(360deg);
			}
		}
		.error {
			color: red;
		}
		.success {
			color: green;
		}
	</style>
</head>
<body>
	<h3>Upload profile</h3>

	<input type="file" id="profileFile" name="uploadProfile" accept=".pdf"><br><br>

	<button id="uploadBtn" onclick="uploadProfile()">Add</button>

	<div id="progress">
		<div id="bar">0%</div>
	</div>

	<h3 id="status"></h3>

	<div id="file-docs"></div>

	<script>
		const MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB — must match UploadProfileServlet

		function validateFile(file) {
			if (!file) {
				return "Please select a file!";
			}
			if (!file.name.toLowerCase().endsWith(".pdf")) {
				return "Error: Invalid file type! Please upload a PDF document.";
			}
			if (file.size > MAX_FILE_SIZE) {
				return "Error: File size exceeds the 5MB limit! Please upload a smaller PDF.";
			}
			return null;
		}

		function resetProgress() {
			const bar = document.getElementById("bar");
			bar.style.width = "0%";
			bar.innerHTML = "0%";
		}

		async function displayProfiles() {
			const response = await fetch("displayProfiles");
			const files = await response.json();
			const container = document.getElementById("file-docs");

			container.innerHTML = "";

			if (files.length === 0) {
				container.innerHTML = "<p>No profile documents uploaded yet.</p>";
				return;
			}

			files.forEach(file => {
				container.innerHTML += '<a href="employee_profiles/' + file + '" target="_blank">' + file + '</a><br>';
			});
		}

		async function uploadProfile() {
			const fileInput = document.getElementById("profileFile");
			const btn = document.getElementById("uploadBtn");
			const status = document.getElementById("status");
			const file = fileInput.files && fileInput.files[0];

			const validationError = validateFile(file);
			if (validationError) {
				status.className = "error";
				status.innerHTML = validationError;
				resetProgress();
				return;
			}

			btn.disabled = true;
			btn.innerHTML = '<span class="loader"></span>';
			status.innerHTML = "";

			const response = await fetch("uploadProgress");
			const reader = response.body.getReader();
			const decoder = new TextDecoder();

			while (true) {
				const { done, value } = await reader.read();
				if (done) break;

				const percent = decoder.decode(value);
				const bar = document.getElementById("bar");

				bar.style.width = percent + "%";
				bar.innerHTML = percent + "%";

				if (percent >= 100) {
					const formData = new FormData();
					formData.append("uploadProfile", fileInput.files[0]);

					const uploadResponse = await fetch("uploadProfile", {
						method: "POST",
						body: formData
					});

					const message = await uploadResponse.text();
					status.className = message.startsWith("Success") ? "success" : "error";
					status.innerHTML = message;

					btn.innerHTML = "Add";
					btn.disabled = false;

					if (message.startsWith("Success")) {
						fileInput.value = "";
						displayProfiles();
					}
				}
			}
		}

		displayProfiles();
	</script>
</body>
</html>
