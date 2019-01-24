package com.onesite.managers;

import org.openqa.selenium.WebDriver;

import com.onesite.pages.CreateComputerPage;
import com.onesite.pages.DeleteComputerPage;
import com.onesite.pages.ReadComputerPage;
import com.onesite.pages.UpdateComputerPage;


public class PageObjectManager {
	
	private WebDriver driver;	
	private ReadComputerPage readComputerPage;
	private CreateComputerPage createComputerPage;
	private DeleteComputerPage deleteComputerPage;
	private UpdateComputerPage updateComputerPage;
	private static PageObjectManager pageObjectManager;
	 
	public PageObjectManager(WebDriver driver) {
		 this.driver = driver;		 
    }
	public static PageObjectManager getpageObjectManagerInstance(WebDriver driver){
		if(pageObjectManager == null){
			return pageObjectManager = new PageObjectManager(driver);
		}else{
			return pageObjectManager;
		}
	}
	public static void setPageObjectManagerInstanceToNull( ){
		pageObjectManager = null;
	}
	public ReadComputerPage getReadComputerPage(){
		if(readComputerPage == null){ return readComputerPage = new ReadComputerPage(driver);
		}else{ return readComputerPage;
		}
	}
	public  void setReadComputerPageInstanceNull(){
		readComputerPage = null;
	}
	public CreateComputerPage getCreateComputerPage(){
		if(createComputerPage == null){	return createComputerPage = new CreateComputerPage(driver);
		}else{ return createComputerPage;
		}
	}
	public void setCreateComputerPageInstanceNull(){
		createComputerPage = null;
	}
	public DeleteComputerPage getDeleteComputerPage(){
		if(deleteComputerPage == null){ return deleteComputerPage = new DeleteComputerPage(driver);			
		}else{ return deleteComputerPage;			
		}
	}
	public void setDeleteComputerPageInstanceToNull(){
		deleteComputerPage = null;
	}
	
	public UpdateComputerPage getUpdateComputerPage(){
		if(updateComputerPage ==  null){ return updateComputerPage = new UpdateComputerPage(driver);			
		}else{ return updateComputerPage;			
		}
	}	
	public void setUpdateComputerPageInstanceToNull(){
		updateComputerPage = null;
	}
	
	
	
}
