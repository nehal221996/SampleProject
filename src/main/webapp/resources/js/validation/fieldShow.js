$(document).ready(function(){
	 $('#google1').hide();
  $('#google').hide();
  $('#login_as').change(function() {
      console.log($(this).val());
      var selected = $(this).val();
      if(selected == 'company' ){
        $('#google').show();
        $('#google1').show();
      }
      else{
        $('#google').hide();
        $('#google1').hide();
      }
  });
  });
    