function logout () {
    var accountDiv = document.createElement("div");
    accountDiv.classList.add("account");

    var logoutButton = document.createElement("button");
    logoutButton.innerHTML = "logout";
    accountDiv.appendChild(logoutButton);

    logoutButton.onclick = function () {
        alert("Do logout here")
    };  // onclick function

    return accountDiv;
}