
//function display() {
////console.log("Hello");
//////Use JQuery AJAX request to post data to a Sling Servlet
    function show() {

            $.ajax({
             type: 'GET',
             url:'/content/digital/en.data.html',
             <!--/* contentType: 'json', */-->

             success: function(data) {
                <!--/* alert(data); */-->
                console.log(data);
                data.filter()
                <!--/* var json = $.parseJSON(data.text); */-->
                <!--/* console.log(json); */-->
             },
             error: function(error) {
                alert(error);
             }
            });
//var hello = "helloo";
//alert(hello);
//console.log("display function");
////return hello;
//};

use(function() {
    function display() {
        console.log("hello");
        document.getElementById("demo").innerHTML = "Hello World";
    };
    return display();

});

