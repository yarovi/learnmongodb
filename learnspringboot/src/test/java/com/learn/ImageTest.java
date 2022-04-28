package com.learn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import com.learn.data.Image;

public class ImageTest {

	@Test
	public void imagesManagerByLombokShouldwokr() {
		Image image = new Image("id","file-name.jpg");
		assertThat(image.getId()).isEqualTo("id");
		assertThat(image.getName()).isEqualTo("file-name.jpg");
		
	}
}
