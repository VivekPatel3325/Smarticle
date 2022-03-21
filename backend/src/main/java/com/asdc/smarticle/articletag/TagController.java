package com.asdc.smarticle.articletag;

import com.asdc.smarticle.comutil.ApplicationUrlPath;
import com.asdc.smarticle.security.JwtUtils;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/smarticleapi/tag")
public class TagController {

	@Autowired
	TagService articletagService;
	
	@Autowired
	JwtUtils jwtUtils;

	@GetMapping(ApplicationUrlPath.RETRIEVE_TAG)
	public List<Tag> getTag(@RequestHeader HttpHeaders http) {
		List<Tag> tags = null;
		String jwtToken = http.getFirst("jwt-token");
		String userName = "";
		if (jwtToken!=null && !jwtToken.isEmpty()) {
			userName = jwtUtils.getUserNameFromJwt(jwtToken);			
		}
		tags = articletagService.getTags(userName);
		return tags;
	}
}
