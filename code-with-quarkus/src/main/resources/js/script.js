function openTest(id){
$.ajax({
  type: "GET",
  url: "http://localhost:8082/api/v1/lesson/" + id,
  success: function (result) {
    console.log(result);
  },
});
}