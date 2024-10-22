package com.exempl.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/posts")
public class PostServlet extends HttpServlet {
    private PostRepository repository;

    @Override
    public void init() {
        repository = new PostRepository();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = repository.all();
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(new Gson().toJson(posts));  
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post = new Gson().fromJson(req.getReader(), Post.class);
        repository.save(post);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        repository.removeById(id);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
