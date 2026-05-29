<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head><title>Index page</title>

<style>
	body {
		font-family: Ariel;
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
    	100%{
			transform: rotate(360deg);
		}
	}
</style>
</head>

<body>
	<h2>TCMS Report Generator</h2>

	<button id="reportBtn" onClick="generateReport()">
		Generate Report
	</button>

    <div id="progress">
        <div id="bar">0%</div>
    </div>

    <h3 id="status"></h3>

    <div id="file-docs"></div>

    <script>
        async function displayReport() {
            const response = await fetch("display");

            const files = await response.json();

            const container = document.getElementById("file-docs");

            container.innerHTML = "";

            files.forEach(file => {
                container.innerHTML += '<a href="reports/' + file + '" target="_blank">' + file + '</a><br>';
            });
        }

        async function generateReport() {
            const btn = document.getElementById("reportBtn");
			
            // disable button
            btn.disabled = true;

            //replace text with tiny loader
            btn.innerHTML = '<span class="loader"></span>';

            /* fetch() is the modern AJAX call

            It sends an asychronous call to your servlet without refreshing the page*/
        
        	const response = await fetch("report");

            const reader = response.body.getReader();
            
            const decoder = new TextDecoder();

            while (true) {
                const { done, value } = await reader.read();

                if (done) break;

                const chunks = decoder.decode(value);

                const percent = chunks;
                
                const bar = document.getElementById("bar");
                
                bar.style.width = percent + "%";
                
                bar.innerHTML = percent + "%";

                if (percent >= 100) {

                    // restore button text
                    btn.innerHTML = "Generate Report";
                    btn.disabled = false;

                    document.getElementById("status").innerHTML = "DONE - Report Generated!";
                    
                    ///////
                    displayReport();
                }
            }
        }
    </script>
</body>
</html>