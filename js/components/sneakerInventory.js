function sneakerInventory() {
    var clickSortContainer = document.createElement("div");
    clickSortContainer.classList.add("clickSort");
    ajax("json/inventory.json", processSneakerInventory, clickSortContainer);

    function processSneakerInventory (list) {

        var inventoryList = [];
        for (var i=0; i <list.length; i++) {
            inventoryList[i] = {};
            inventoryList[i].sneakerName = list[i].sneakerName;
            inventoryList[i].image = "<img src='" + list[i].image + "' style=width:5rem'>";
            inventoryList[i].currentValue = list[i].currentValue;
            inventoryList[i].releaseDate = list[i].releaseDate;
            inventoryList[i].webUserId = list[i].webUserId;
        }

        var params = {
            list: inventoryList,
            sortOrderPropName: "sneakerName",
            sortIcon: "icon/sortUpDown16.png",
            title: "Sneaker Inventory"
        }

        var myClickSort = makeClickSort(params);
        clickSortContainer.appendChild(myClickSort);

        var addSneaker = document.createElement("button");
        addSneaker.innerHTML = "Add Sneaker";
        addSneaker.onclick = function() {window.location.hash = "#/addSneaker"};
        clickSortContainer.appendChild(addSneaker);

    }
    return clickSortContainer;
}