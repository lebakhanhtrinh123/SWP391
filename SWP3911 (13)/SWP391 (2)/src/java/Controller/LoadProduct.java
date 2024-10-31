/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Category;
import Model.Material;
import Model.Product;
import Dao.CategoryDAO;
import Dao.ProductDAO;
import Dao.MaterialDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */

public class LoadProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            ProductDAO pdo = new ProductDAO();
             Product p = pdo.getProductByID(id);
            CategoryDAO cdo = new CategoryDAO();
            List<Category> listCate = cdo.getAll();
            MaterialDAO mdo = new MaterialDAO();
            List<Material> listMate = mdo.getAll();
          
            /* TODO output your page here. You may use following sample code. */
            out.println("<form action=\"UpdateProduct\" method=\"POST\" id=\"updatesp\">\n"
                    + "                            <div class=\"row\">\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Mã sản phẩm </label>\n"
                    + "                                    <input readonly=\"true\" class=\"form-control\" type=\"text\" name=\"masp\" value=\"" + p.getProductID() + "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Tên sản phẩm</label>\n"
                    + "                                    <input class=\"form-control\" type=\"text\" required name=\"tensp\" id=\"tensp\" value=\"" + p.getName()+ "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Hình dáng sản phẩm</label>\n"
                    + "                                    <input class=\"form-control\" type=\"text\" required name=\"sizesp\" id=\"size\" value=\"" + p.getSize()+ "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Giá bán</label>\n"
                    + "                                    <input class=\"form-control\" type=\"number\"required name=\"giasp\" value=\"" + p.getPrice() + "\" id=\"minPrice\" onkeydown=\"return event.key !== '-'\" step=\"any\">\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Số lượng</label>\n"
                    + "                                    <input class=\"form-control\" type=\"number\"required name=\"quantity\" value=\"" + p.getStock()+ "\" id=\"minPrice\" onkeydown=\"return event.key !== '-'\" step=\"any\">\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Discount</label>\n"
                    + "                                    <input class=\"form-control\" type=\"number\" step=\"0.01\" name=\"discountsp\" value=\"" + p.getDiscount()+ "\" id=\"minPrice\" min=\"0\" max=\"100\" step=\"any\" onkeydown=\"return event.key !== '-'\">\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label for=\"exampleSelect1\" class=\"control-label\">Danh mục</label>\n"
                    + "                                    <select class=\"form-control\" name=\"categorysp\">\n");
                                                            
                                                            for(Category i: listCate){
                                                                if (i.getId() == p.getCategory().getId()) {
                                                                    out.println("<option selected value=\"" + i.getId() + "\">" + i.getName() + "</option>");
                                                                } else {
                                                                    out.println("<option value=\"" + i.getId() + "\">" + i.getName() + "</option>");
                                                                }
                                                            }
                                                            out.println("                                    </select>\n");
                                                            out.println("                                </div>\n"
                                                                        
                    + "                            </div>\n"
                    + "                            <BR>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label for=\"exampleSelect1\" class=\"control-label\">Nguyen lieu</label>\n"
                    + "                                    <select class=\"form-control\" name=\"materialsp\">\n");
                                                            
                                                            for(Material i: listMate){
                                                                if (i.getId()== p.getMaterial()) {
                                                                    out.println("<option selected value=\"" + i.getId() + "\">" + i.getOriginal()+ "</option>");
                                                                } else {
                                                                    out.println("<option value=\"" + i.getId() + "\">" + i.getOriginal()+ "</option>");
                                                                }
                                                            }
                                                            out.println("                                    </select>\n");
                                                            out.println("                                </div>\n"
                    + "                            </div>\n"
                    
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\"></label>\n"
                    + "     <img  src=\"img/" + p.getImage()+ "\" id=\"avatarPreview\" alt=\"User cc\" width=\"100px\">"
                    +"     <input type=\"file\" name=\"image\" id=\"newAvatar\" onchange=\"previewImage(this) ;\">"
                    +"      <input type=\"hidden\" name=\"oldimage\" value=\"" + p.getImage() + "\">\n"
                            + "    </div>\n"    
                    + "  </div>\n");
                    out.print(
                   "<script>\n" +

                   "function previewImage(input) {\n" +
                   "    var avatarPreview = document.getElementById('avatarPreview');\n" +
                       "var oldImageValue = document.querySelector(\"input[name='oldimage']\").value;"+
                   "    if (input.files && input.files[0]) {\n" +
                   "        var reader = new FileReader();\n" +
                   "        reader.onload = function (e) {\n" +
                   "            avatarPreview.src = e.target.result;\n" +
                   "        };\n" +
                   "        reader.readAsDataURL(input.files[0]);\n" +
                   "    } else {\n" +
                   "      avatarPreview.src=oldImageValue  "+
                   "    }\n" +
                   "}\n" +
                   "</script>"

                    + "                            <button class=\"btn btn-save\" type=\"submit\">Lưu lại</button>\n"
                    + "                            <a class=\"btn btn-cancel\" data-dismiss=\"modal\" href=\"ManagerProductController\">Hủy bỏ</a>\n"
                    + "                            <BR>\n"
                    + "                        </form>"
                    + "<script>\n"
                    + "   function validateForm() {\n"
        + " var tensp = document.getElementById(\"tensp\").value.trim();\n" +
"                            var quantity = document.getElementById(\"quantity\").value.trim();\n console.log(quantity + '------')" +
"                            var price = document.getElementById(\"price\").value.trim();\n" +
"                            var discount = document.getElementById(\"discount\").value.trim();\n" +
"                            var code = document.getElementById(\"code\").value.trim();\n" +
"                            var size = document.getElementById(\"size\").value.trim();\n" +
"                            \n" +
"                            var imageError = document.getElementById(\"imageError\");\n" +
"\n" +
"                            var isValid = true;\n" +
"\n" +
"                            if (tensp === \"\") {\n" +
"                                document.getElementById(\"tensp\").classList.add(\"error-input\");\n" +
"                                document.getElementById(\"tensp-error\").textContent = \"Vui lòng nhập tên sản phẩm.\";\n" +
"                                document.getElementById(\"tensp-error\").style.color = \"red\";\n" +
"                                document.getElementById(\"tensp\").style.borderColor = \"red\";\n" +
"                                isValid = false;\n" +
"                            } else {\n" +
"                                document.getElementById(\"tensp\").classList.remove(\"error-input\");\n" +
"                                document.getElementById(\"tensp-error\").textContent = \"\";\n" +
"                                document.getElementById(\"tensp-error\").style.color = \"\";\n" +
"                                document.getElementById(\"tensp\").style.borderColor = \"\";\n" +
"                            }\n" +
"\n" +
"                            if (quantity === \"\") {\n" +
"                                document.getElementById(\"quantity\").classList.add(\"error-input\");\n" +
"                                document.getElementById(\"quantity-error\").textContent = \"Vui lòng nhập số lượng.\";\n" +
"                                document.getElementById(\"quantity-error\").style.color = \"red\";\n" +
"                                document.getElementById(\"quantity\").style.borderColor = \"red\";\n" +
"                                isValid = false;\n" +
"                            } else if(quantity <=0){\n" +
"                                 document.getElementById(\"quantity\").classList.add(\"error-input\");\n" +
"                                document.getElementById(\"quantity-error\").textContent = \"Số lượng vui lòng lớn hơn 0.\";\n" +
"                                document.getElementById(\"quantity-error\").style.color = \"red\";\n" +
"                                document.getElementById(\"quantity\").style.borderColor = \"red\";\n" +
"                                isValid = false;\n" +
"                                \n" +
"                            } else if(isDecimal(quantity)){\n" +
"                                 document.getElementById(\"quantity\").classList.add(\"error-input\");\n" +
"                                document.getElementById(\"quantity-error\").textContent = \"Số lượng không thể là số thập phân.\";\n" +
"                                document.getElementById(\"quantity-error\").style.color = \"red\";\n" +
"                                document.getElementById(\"quantity\").style.borderColor = \"red\";\n" +
"                                isValid = false;\n" +
"                                \n" +
"                            }\n" +
"                            else {\n" +
"                                document.getElementById(\"quantity\").classList.remove(\"error-input\");\n" +
"                                document.getElementById(\"quantity-error\").textContent = \"\";\n" +
"                                document.getElementById(\"quantity-error\").style.color = \"\";\n" +
"                                 document.getElementById(\"quantity\").style.borderColor = \"\";\n" +
"                            }\n" +
"\n" +
"                            if (code === \"\") {\n" +
"                                document.getElementById(\"code\").classList.add(\"error-input\");\n" +
"                                document.getElementById(\"code-error\").textContent = \"Vui lòng nhập mã sản phẩm.\";\n" +
"                                document.getElementById(\"code-error\").style.color = \"red\";\n" +
"                                document.getElementById(\"code\").style.borderColor = \"red\";\n" +
"                                isValid = false;\n" +
"                            } \n" +
"                            else {\n" +
"                                document.getElementById(\"code\").classList.remove(\"error-input\");\n" +
"                                document.getElementById(\"code-error\").textContent = \"\";\n" +
"                                document.getElementById(\"code-error\").style.color = \"\";\n" +
"                                 document.getElementById(\"code\").style.borderColor = \"\";\n" +
"                            }\n" +
"                            \n" +
"                            if (size === \"\") {\n" +
"                                document.getElementById(\"size\").classList.add(\"error-input\");\n" +
"                                document.getElementById(\"size-error\").textContent = \"Vui lòng nhập hình dáng và kích cỡ.\";\n" +
"                                document.getElementById(\"size-error\").style.color = \"red\";\n" +
"                                document.getElementById(\"size\").style.borderColor = \"red\";\n" +
"                                isValid = false;\n" +
"                            } \n" +
"                            else {\n" +
"                                document.getElementById(\"size\").classList.remove(\"error-input\");\n" +
"                                document.getElementById(\"size-error\").textContent = \"\";\n" +
"                                document.getElementById(\"size-error\").style.color = \"\";\n" +
"                                 document.getElementById(\"size\").style.borderColor = \"\";\n" +
"                            }\n" +
"\n" +
"\n" +
"                            if (price === \"\") {\n" +
"                                document.getElementById(\"price\").classList.add(\"error-input\");\n" +
"                                document.getElementById(\"price-error\").textContent = \"Vui lòng nhập giá bán.\";\n" +
"                                document.getElementById(\"price-error\").style.color = \"red\";\n" +
"                                document.getElementById(\"price\").style.borderColor = \"red\";\n" +
"                                isValid = false;\n" +
"                            } else if(price <= 0)\n" +
"                            {\n" +
"                                 document.getElementById(\"price\").classList.add(\"error-input\");\n" +
"                                document.getElementById(\"price-error\").textContent = \"Giá bán lớn hơn 0.\";\n" +
"                                document.getElementById(\"price-error\").style.color = \"red\";\n" +
"                                document.getElementById(\"price\").style.borderColor = \"red\";\n" +
"                                isValid = false;\n" +
"                                \n" +
"                                \n" +
"                            }\n" +
"                            else {\n" +
"                                document.getElementById(\"price\").classList.remove(\"error-input\");\n" +
"                                document.getElementById(\"price-error\").textContent = \"\";\n" +
"                                document.getElementById(\"price-error\").style.color = \"\";\n" +
"                                document.getElementById(\"price\").style.borderColor = \"\";\n" +
"                            }\n" +
"\n" +
"                            if (discount === \"\") {\n" +
"                                document.getElementById(\"discount\").classList.add(\"error-input\");\n" +
"                                document.getElementById(\"discount-error\").textContent = \"Vui lòng nhập discount.\";\n" +
"                                document.getElementById(\"discount-error\").style.color = \"red\";\n" +
"                                document.getElementById(\"discount\").style.borderColor = \"red\";\n" +
"                                isValid = false;\n" +
"                            } else if (discount < 0 || discount > 100){\n" +
"                                document.getElementById(\"discount\").classList.add(\"error-input\");\n" +
"                                document.getElementById(\"discount-error\").textContent = \" Discount vui lòng lớn hơn 0 và nhỏ hơn 100.\";\n" +
"                                document.getElementById(\"discount-error\").style.color = \"red\";\n" +
"                                document.getElementById(\"discount\").style.borderColor = \"red\";\n" +
"                                isValid = false;\n" +
"                                \n" +
"                            }\n" +
"                                \n" +
"                            else {\n" +
"                                document.getElementById(\"discount\").classList.remove(\"error-input\");\n" +
"                                document.getElementById(\"discount-error\").textContent = \"\";\n" +
"                                document.getElementById(\"discount-error\").style.color = \"\";\n" +
"                                  document.getElementById(\"discount\").style.borderColor = \"\";\n" +
"                            }\n" 
                           + 
"                                document.getElementById(\"updatesp\").submit();\n" +
"                            }"
        + "</script>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
