package app.common;

import app.admin.service.CategoryService;
import app.admin.service.ImageService;
import app.admin.service.ModelService;


public class Factory {
	public static CategoryService getCategoryService(){return (CategoryService) BeanFactory.getBean("categoryService");}
	public static ModelService getModelService(){return (ModelService) BeanFactory.getBean("modelService");}
	public static ImageService getImageService(){return (ImageService) BeanFactory.getBean("imageService");}
}
