package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.Assert;

public class TasksTest {
	
	public WebDriver acessoAplication() throws MalformedURLException {
		DesiredCapabilities browser = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.153:4444/wd/hub"), browser);
		driver.navigate().to("http://192.168.1.153:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessoAplication();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste seleinium 3");
			driver.findElement(By.id("dueDate")).sendKeys("25/02/2023");
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Sucess!", message);
		}finally {
			driver.quit();			
		}
	}
	
	
	@Test
	public void NaoSalvaDataPassada() throws MalformedURLException {
		WebDriver driver = acessoAplication();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste seleinium 3");
			driver.findElement(By.id("dueDate")).sendKeys("10/01/2023");
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Due date must not be in past", message);
		}finally {
			driver.quit();			
		}
	}
	
	@Test
	public void NaoSalvaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessoAplication();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("dueDate")).sendKeys("25/02/2023");
			driver.findElement(By.id("saveButton")).click();
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the task description", message);
		}finally {
			driver.quit();			
		}
	}
	
	@Test
	public void NaoSalvaSemData() throws MalformedURLException {
		WebDriver driver = acessoAplication();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste seleinium 5");
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the due date", message);
		}finally {
			driver.quit();			
		}
	}
}
