function enter_search(e) {
  if(e.keyCode == 13){
    document.formm.action = "NonageServlet?command=admin_product_search";
    document.formm.submit();
  }
}

function go_search(){
    document.formm.action = "NonageServlet?command=admin_product_search";
    document.formm.submit();
}