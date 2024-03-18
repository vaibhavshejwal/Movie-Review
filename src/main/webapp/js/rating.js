$(document).ready(function() {  
          $("#st1").click(function() {  
              $(".fa-star").css("color", "black");  
              $("#st1").css("color", "yellow");  
  
          });  
          $("#st2").click(function() {  
              $(".fa-star").css("color", "black");  
              $("#st1, #st2").css("color", "yellow");  
  
          });  
          $("#st3").click(function() {  
              $(".fa-star").css("color", "black")  
              $("#st1, #st2, #st3").css("color", "yellow");  
  
          });  
          $("#st4").click(function() {  
              $(".fa-star").css("color", "black");  
              $("#st1, #st2, #st3, #st4").css("color", "yellow");  
  
          });  
          $("#st5").click(function() {  
              $(".fa-star").css("color", "black");  
              $("#st1, #st2, #st3, #st4, #st5").css("color", "yellow");  
  
          });  
        }); 