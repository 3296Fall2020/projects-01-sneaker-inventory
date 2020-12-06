function logout () {
    var accountDiv = document.createElement("div");
    accountDiv.classList.add("account");


    var logoutButton = document.createElement("input");
    logoutButton.setAttribute("type", "button");
    logoutButton.setAttribute("value", "Log out");
    accountDiv.appendChild(logoutButton);

    logoutButton.onclick = function () {
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.withCredentials = true;
        var theUrl = "https://54.172.190.202:443/logout";

        xmlhttp.open("DELETE", theUrl);
        xmlhttp.send();
        alert("Logged out");
        window.location.hash = "#/home";

    }

    return accountDiv;
}