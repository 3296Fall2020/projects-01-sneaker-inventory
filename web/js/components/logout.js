function logout () {
    var accountDiv = document.createElement("div");
    accountDiv.classList.add("account");


    var logoutButton = document.createElement("input");
    logoutButton.setAttribute("type", "button");
    logoutButton.setAttribute("value", "Log out");
    accountDiv.appendChild(logoutButton);

    logoutButton.onclick = function () {
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        var theUrl = "http://54.172.190.202:8080/logout";

        xmlhttp.open("DELETE", theUrl);
        alert("Logged out");
        window.location.hash = "#/home";

    }

    return accountDiv;
}