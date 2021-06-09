package com.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.user.User;

@Controller
public class MovieController {

	@Autowired
	private MovieRepository movieRepo;
	
	@GetMapping("/movies")
	public String showMovieList(Model model) {
		model.addAttribute("user", new User());
		List<Movie> listMovies = movieRepo.findAll();
		model.addAttribute("listMovies", listMovies);
		return "movies";
	}
	
	@GetMapping("/movies/new")
	public String showCreateNewMovieForm(Model model) {
		model.addAttribute("movie", new Movie());
		
		return "movie_form";
	}
	
	@PostMapping("/movies/save")
	public String saveMovie(Movie movie) {
		movieRepo.save(movie);
		
		return "redirect:/movies";
	}
	
	@GetMapping("/movies/edit/{id}")
	public String showEditMovieForm(@PathVariable("id") Integer id, Model model) {
		Movie movie = movieRepo.findById(id).get();
		model.addAttribute("movie", movie);
		
		return "movie_form";
	}
	
	@GetMapping("/movies/delete/{id}")
	public String deleteMovie(@PathVariable("id") Integer id) {
		movieRepo.deleteById(id);
		
		return "redirect:/movies";
	}
	
}
