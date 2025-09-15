package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;
import service.EmployeeService;
import service.EmployeeServiceImpl;

@WebServlet(name = "EmployeeServlet", urlPatterns = { "/employees", "" })
public class EmployeeServlet extends HttpServlet {
  private EmployeeService employeeService = new EmployeeServiceImpl();

  @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action == null || action.isEmpty()) {
        // Mặc định chạy listEmployees
        listEmployees(request, response);
        return;
    }

    switch (action) {
      case "create":
        showCreateForm(request, response);
        break;
      case "edit":
        showEditForm(request, response);
        break;
      case "delete":
        showDeleteForm(request, response);
        break;
      default:
        listEmployees(request, response);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action == null) {
      action = "";
    }

    switch (action) {
      case "create":
        createEmployee(request, response);
        break;
      case "edit":
        updateEmployee(request, response);
        break;
      case "delete":
        deleteEmployee(request, response);
        break;
      default:
        response.sendRedirect("employees");
        break;
    }
  }

  private void listEmployees(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Employee> employees = employeeService.findAll();
    request.setAttribute("employees", employees);
    RequestDispatcher dispatcher = request.getRequestDispatcher("employee/listEmp.jsp");
    dispatcher.forward(request, response);
  }

  private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("employee/createEmp.jsp");
    dispatcher.forward(request, response);
  }

  private void createEmployee(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String address = request.getParameter("address");

    Employee employee = new Employee(id, name, email, address);
    employeeService.save(employee);
    response.sendRedirect("employees");
  }

  private void showEditForm(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Employee employee = employeeService.findById(id);
    request.setAttribute("employee", employee);
    RequestDispatcher dispatcher = request.getRequestDispatcher("employee/editEmp.jsp");
    dispatcher.forward(request, response);
  }

  private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String address = request.getParameter("address");

    Employee employee = new Employee(id, name, email, address);
    employeeService.update(id, employee);
    response.sendRedirect("employees");
  }

  private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Employee employee = employeeService.findById(id);
    request.setAttribute("employee", employee);
    RequestDispatcher dispatcher = request.getRequestDispatcher("employee/deleteEmp.jsp");
    dispatcher.forward(request, response);
  }

  private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    employeeService.remove(id);
    response.sendRedirect("employees");
  }
}