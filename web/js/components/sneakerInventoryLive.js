function sneakerInventoryLive() {
    var clickSortContainer = document.createElement("div");
    clickSortContainer.classList.add("clickSort");
    ajax("https://54.172.190.202:443/inventory", processSneakerInventory, clickSortContainer);

    function processSneakerInventory (list) {

        var inventoryList = [];
        for (var i=0; i <list.length; i++) {
            inventoryList[i] = {};
            inventoryList[i].shoeName = list[i].shoeName;
            inventoryList[i].sku = list[i].sku;
            inventoryList[i].size = list[i].size;
            inventoryList[i].price = list[i].price;
            inventoryList[i].userId = list[i].userId;
        }

        var params = {
            list: inventoryList,
            sortOrderPropName: "shoeName",
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