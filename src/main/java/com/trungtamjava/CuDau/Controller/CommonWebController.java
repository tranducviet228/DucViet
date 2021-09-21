package com.trungtamjava.CuDau.Controller;

import java.io.File;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonWebController {
    @GetMapping(value = "/download")
    public void downFile(HttpServletResponse response, @RequestParam("image") String image) {
		final String uploadFodel= "D:\\anhao\\";
		File file= new File(uploadFodel + File.separator + image);
		if(file.exists()) {
			try {
				Files.copy(file.toPath(), response.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	
		
    	
    }
    @GetMapping(value = "/about")
    public String about() {
    	return "client/about";
    }
    @GetMapping(value = "/contact")
    public String contact() {
    	return "client/contact";
    }
}
