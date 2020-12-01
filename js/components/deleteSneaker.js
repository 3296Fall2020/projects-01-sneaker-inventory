function deleteSneaker () {
    var accountDiv = document.createElement("div");
    accountDiv.classList.add("account");

    var confirm = document.createElement("span");
    confirm.innerHTML("Confirm Delete  ");
    accountDiv.appendChild(confirm);

    var deleteButton = document.createElement("input");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("value", "Delete Sneaker");
    accountDiv.appendChild(deleteButton);

    logoutButton.onclick = function () {
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        var theUrl = "https://54.172.190.202:443/deleteSneaker";

        xmlhttp.open("POST", theUrl);
        alert("Sneaker Deleted");
        window.location.hash = "#/sneakerInventory";

    }

    return accountDiv;
}