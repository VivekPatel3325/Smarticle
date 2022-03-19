package com.asdc.smarticle.articletag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asdc.smarticle.user.User;
import com.asdc.smarticle.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	TagRepository articletagRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<Tag> getTags(String userName) {
		System.out.println("userName" + userName);
		User user = null;
		if (!userName.isEmpty()) {
			user = userRepository.findByUserName(userName);
		}
		if (userName.isEmpty() || (user != null && user.getTags().isEmpty())) {
			System.out.println();
			return articletagRepository.findAll();
		} else {
			Set<Tag> setOfTags = user.getTags();
			return new ArrayList<>(setOfTags);
		}
	}
}
