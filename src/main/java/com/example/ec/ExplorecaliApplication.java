package com.example.ec;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ec.domain.Difficulty;
import com.example.ec.domain.Region;
import com.example.ec.service.TourPackageService;
import com.example.ec.service.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ExplorecaliApplication implements CommandLineRunner {

	// CommandLineRunner implemented because class doesn't have access to service
	// class as they are static.

	@Autowired
	private TourService tourService;

	@Autowired
	private TourPackageService tourPackageService;

	public static void main(String[] args) {
		SpringApplication.run(ExplorecaliApplication.class, args);
	}

	/**
	 * Before accepting any web request, this run method will be executed.
	 * It is setting up the database and then get back the result from DB.
	 */
	@Override
	public void run(String... args) throws Exception {
		tourPackageService.createTourPackage("BC", "Backpack Cal");
		tourPackageService.createTourPackage("CC", "California Calm");
		tourPackageService.createTourPackage("CH", "California Hot Springs");
		tourPackageService.createTourPackage("CY", "Cycle California");
		tourPackageService.createTourPackage("DS", "From Desert to Sea");
		tourPackageService.createTourPackage("KC", "Kids California");
		tourPackageService.createTourPackage("NW", "Nature Watch");
		tourPackageService.createTourPackage("SC", "Snowboard Cali");
		tourPackageService.createTourPackage("TC", "Taste of California");
		tourPackageService.lookup().forEach(tourPackage -> System.out.println(tourPackage));
		TourFromFile.importTours()
				.forEach(t -> tourService.createTour(Integer.parseInt(t.id), t.title, t.description, t.blurb,
						Integer.parseInt(t.price), t.length, t.bullets, t.keywords, t.packageType,
						Difficulty.valueOf(t.difficulty), Region.findByLabel(t.region)));
		System.out.println("Number of tours : " + tourService.total());
	}

	static class TourFromFile {

		private String id, packageType, title, description, blurb, price, length, bullets, keywords, difficulty, region;

		static List<TourFromFile> importTours() throws IOException {
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).readValue(
					TourFromFile.class.getResourceAsStream("/ExploreCalifornia.json"),
					new TypeReference<List<TourFromFile>>() {
					});

		}
	}

}
