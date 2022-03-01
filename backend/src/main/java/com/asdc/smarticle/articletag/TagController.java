package com.asdc.smarticle.articletag;

import com.asdc.smarticle.comutil.ApplicationUrlPath;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/smarticleapi/tag")
public class TagController {

	@Autowired
	TagService articletagService;

	@GetMapping(ApplicationUrlPath.RETRIEVE_TAG)
	public List<Tag> getTag() {
		List<Tag> tags = articletagService.getTags();
		return tags;
	}
}
