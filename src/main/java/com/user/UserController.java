package com.user;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.movie.Movie;
import com.movie.MovieRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MovieRepository movieRepo;
	
	@GetMapping("/users")
	public String showUserList(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	@GetMapping("/users/new")
	public String showCreateNewUserForm(Model model) {
		model.addAttribute("user", new User());
		
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		userRepo.save(user);
		
		return "redirect:/users";
	}
	
	@GetMapping("/users/addMovie/{id}")
	public String addMovie(@PathVariable("id") Integer id, Model model) {
		User user = userRepo.findById(id).get();
		model.addAttribute("user", user);
		
		List<Movie> listMovies = movieRepo.findAll();
		model.addAttribute("listMovies", listMovies);
		
		return "movies";
	}
	
	@GetMapping("/users/add/{userId}/{movieId}")
	public String add(@PathVariable("userId") Integer userId, @PathVariable("movieId") Integer movieId, Model model) {
		//finish this later
		User user = userRepo.findById(userId).get();
		Movie movie = movieRepo.findById(movieId).get();
		Set<Movie> movies = user.getMovies();
		movies.add(movie);
		user.setMovies(movies);
		userRepo.save(user);
		
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String showEditUserForm(@PathVariable("id") Integer id, Model model) {
		User user = userRepo.findById(id).get();
		model.addAttribute("user", user);
		
		return "user_form";
	}
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id) {
		userRepo.deleteById(id);
		
		return "redirect:/users";
	}
}