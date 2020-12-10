function routeFw(params) {

    var fw = {}; // creating and adorning this object to be passed back to the HTML page.

    var contentId = params.contentId || "content";

    if (!params.routeArray || params.routeArray[0]) {
        alert("parameter object must specify array 'routeArray' with at least one element");
        return;
    }

    // Declare a (private) array to store our routes. This array is an associative
    // array with key [index] the URL and value the associated function to run.
    var routes = params.routeArray;

    function inject(what) {
        document.getElementById(contentId).innerHTML = "";
        document.getElementById(contentId).appendChild(what);
    }

    // private function that will be called whenever a link is clicked (or href changed)
    function router() {

        var path = location.hash;
        console.log('path is ' + path); // prints something like #/home


        if (!routes[path]) {
            document.getElementById(contentId).innerHTML = "Error: link '" + path +
                "' was never added to the routing table.";
        } else {
            var ele = routes[path](contentId);
            inject(ele);
        }
    }

    fw.printRoutes = function () {
        console.log("routes will be printed on the next line ");
        console.log(routes);
    };

    // Listen on hash change (whenever a link is clicked or href programmatically changed)
    // then invoke function router.
    window.addEventListener('hashchange', router);

    // content for when page is first rendered.
    window.location.hash = "#/";

    return fw;
}