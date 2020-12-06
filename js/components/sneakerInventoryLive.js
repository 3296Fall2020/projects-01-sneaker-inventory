function sneakerInventoryLive() {
    var clickSortContainer = document.createElement("div");
    clickSortContainer.classList.add("clickSort");
    ajaxLive("https://54.172.190.202:443/inventory", processSneakerInventory, clickSortContainer);

    function processSneakerInventory (array) {


        var inventoryList = [];
        for (var i=0; i <array.length; i++) {
            inventoryList[i] = {};
            inventoryList[i].shoeName = array[i].shoeName;
            inventoryList[i].sku = array[i].sku;
            inventoryList[i].size = array[i].size;
            inventoryList[i].price = array[i].price;
            inventoryList[i].userId = array[i].userId;
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