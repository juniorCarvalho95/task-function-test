package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class TasksTest {
	
	public WebDriver acessoAplication() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
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
	public void NaoSalvaDataPassada() {
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
	public void NaoSalvaSemDescricao() {
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
	public void NaoSalvaSemData() {
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
