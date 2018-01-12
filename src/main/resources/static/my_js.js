function registerShowPassword() {
    var x = document.getElementById("registerPassword");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

 var asyncRequest;
       function startRandomPrediction(){

                    $.ajax({
                    type: "POST",
                    url: "/random",
                    success: function(data)
                    {
                    alert(data);



                    return draw_histogram(data);

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                    alert(thrownError);
                    },
                    async:false
                    });
}




var asyncRequest;
       function startBackPropagation(){

                    $.ajax({
                    type: "POST",
                    url: "/backpropagation",
                    success: function(data)
                    {
                    alert(data);



                    return draw_histogram(data);

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                    alert(thrownError);
                    },
                    async:false
                    });
}


var asyncRequest;
       function startBackPropagationRealValues(){

                    $.ajax({
                    type: "POST",
                    url: "/backpropagationV",
                    success: function(data)
                    {
                    alert(data);



                    return draw_histogram(data);

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                    alert(thrownError);
                    },
                    async:false
                    });
}



var asyncRequest;
       function startCasCorP(){

                    $.ajax({
                    type: "POST",
                    url: "/cascor",
                    success: function(data)
                    {
                    alert(data);

                    return draw_histogram(data);

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                    alert(thrownError);
                    },
                    async:false
                    });
}



var asyncRequest;
       function startEMA(){

                    $.ajax({
                    type: "POST",
                    url: "/ema",
                    success: function(data)
                    {
                    alert(data);

                    return draw_histogram(data);

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                    alert(thrownError);
                    },
                    async:false
                    });
}

var asyncRequest;
       function startHomeostatic(){

                    $.ajax({
                    type: "POST",
                    url: "/homeostatic",
                    success: function(data)
                    {
                    alert(data);

                    return draw_histogram(data);

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                    alert(thrownError);
                    },
                    async:false
                    });
}



var asyncRequest;
       function startTendencyBased(){

                    $.ajax({
                    type: "POST",
                    url: "/tendency",
                    success: function(data)
                    {
                    alert(data);

                    return draw_histogram(data);

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                    alert(thrownError);
                    },
                    async:false
                    });
}

var asyncRequest;
       function startUnix(){

                    $.ajax({
                    type: "POST",
                    url: "/unix",
                    success: function(data)
                    {
                    alert(data);

                    return draw_histogram(data);

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                    alert(thrownError);
                    },
                    async:false
                    });
}

var asyncRequest;
       function startWMA(){

                    $.ajax({
                    type: "POST",
                    url: "/wma",
                    success: function(data)
                    {
                    alert(data);

                    return draw_histogram(data);

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                    alert(thrownError);
                    },
                    async:false
                    });
}


function draw_histogram (data)
{



var m = [80, 80, 80, 80];
var w = 1000 - m[1] - m[3];
var h = 400 - m[0] - m[2];
var x = d3.scale.linear().domain([0, data.length]).range([0, w]);
var y = d3.scale.linear().domain([0, 10]).range([h, 0]);

var line = d3.svg.line()
.x(function(d,i) {
console.log('Plotting X value for data point: ' + d + ' using index: ' + i + ' to be at: ' + x(i) + ' using our xScale.');
return x(i);
})
.y(function(d) {
console.log('Plotting Y value for data point: ' + d + ' to be at: ' + y(d) + " using our yScale.");
return y(d);
})

var line2 = d3.svg.line()
            .x(function(d,i) {
            console.log('Plotting X value for data point: ' + d + ' using index: ' + i + ' to be at: ' + x(i) + ' using our xScale.');
            return x(i);
            })
            .y(function(d) {
            console.log('Plotting Y value for data point: ' + d + ' to be at: ' + 150 + " using our yScale.");
            return 150;
            })

var graph = d3.select("#graph").append("svg:svg")
.attr("width", w + m[1] + m[3])
.attr("height", h + m[0] + m[2])
.append("svg:g")
.attr("transform", "translate(" + m[3] + "," + m[0] + ")");

var xAxis = d3.svg.axis().scale(x).tickSize(-h).tickSubdivide(true);
graph.append("svg:g")
.attr("class", "x axis")
.attr("transform", "translate(0," + h + ")")
.call(xAxis);


var yAxisLeft = d3.svg.axis().scale(y).ticks(4).orient("left");
graph.append("svg:g")
.attr("class", "y axis")
.attr("transform", "translate(-25,0)")
.call(yAxisLeft);

graph.append("svg:path").attr("d", line(data));
graph.append("svg:path").attr("d", line2(data));



}





function loginShowPassword() {
    var x = document.getElementById("loginPassword");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

function login(){
    var myusername = $("#exampleInputEmail1").val();
    var mypassword = $("#exampleInputPassword1").val();
    $.ajax({
      type: "POST",
      url: "/loginJava",
      data: {username : myusername, password : mypassword},
      success: function(){
         alert("success");
      },
      error: function (xhr, ajaxOptions, thrownError) {
              alert(xhr.status);
              alert(thrownError);
            }
    });
}