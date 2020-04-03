package src.main.java.com.mediamovimiento;

import src.main.java.com.mediamovimiento.model.Post;
import src.main.java.com.mediamovimiento.repo.PostRepository;

public class MainApp {

    public static void main(String... args) {
        Long postId = 1L;
        Post post = null;
        PostRepository postRepository = new PostRepository();

        //Using EntityManager.find().
        post = postRepository.find(postId);
        System.out.println("1.post.subject="+post.getSubject());
        post = postRepository.findWithEntityGraph(postId);
        System.out.println("2.post.subject="+post.getSubject());
        post = postRepository.findWithEntityGraph2(postId);
        System.out.println("3.post.subject="+post.getSubject());
        //Using JPQL: Query and TypedQuery
        post = postRepository.findUsingJpql(postId);
        System.out.println("4.post.subject="+post.getSubject());
        //Using Criteria API
        post = postRepository.findUsingCriteria(postId);
        System.out.println("5.post.subject="+post.getSubject());
        postRepository.clean();
    }
}