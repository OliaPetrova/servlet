package com.exempl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

@WebServlet(urlPatterns = "/posts/*")
public class PostServlet extends HttpServlet {
    private PostController controller;

    @Override
    public void init() throws ServletException {
        final var repository = new PostRepository();
        final var service = new PostService(repository);
        controller = new PostController(service);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 List<Post> posts = controller.allPosts();
         resp.setContentType("application/json");
         resp.setCharacterEncoding("UTF-8");
         PrintWriter writer = resp.getWriter();
         String json = new Gson().toJson(posts);
         writer.write(json);
         writer.flush();
         writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post = new Gson().fromJson(req.getReader(), Post.class);
        controller.savePost(post);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getPathInfo().substring(1));
        controller.deletePost(id);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
