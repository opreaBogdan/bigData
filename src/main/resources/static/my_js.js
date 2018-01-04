function registerShowPassword() {
    var x = document.getElementById("registerPassword");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
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