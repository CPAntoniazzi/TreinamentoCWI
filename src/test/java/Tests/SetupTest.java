package Tests;

import io.qameta.allure.Story;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import pageObjects.*;
import utils.Browser;
import utils.Utils;

import static org.junit.Assert.*;

public class SetupTest extends BaseTests {

    @Test
    public void testOpeningBrowserAndLoadingPage(){
        assertTrue(Browser.getCurrentDriver().getCurrentUrl().contains(Utils.getBaseUrl()));
        System.out.println("Abrimos o navegador e carregamos a url");
    }

    @Test
    public void testLogin(){

        Browser.getCurrentDriver().findElement(By.className("login")).click();
        System.out.println("Clicou em sign in e direcionou para pagina de login");
        assertTrue(Browser.getCurrentDriver().getCurrentUrl()
                .contains(Utils.getBaseUrl().concat("index.php?controller=authentication&back=my-account")));
        Browser.getCurrentDriver().findElement(By.id("email")).sendKeys("rrsetcwi+autopractice@gmail.com");
        System.out.println("Preencheu o email");
        Browser.getCurrentDriver().findElement(By.id("passwd")).sendKeys("teste123");
        System.out.println("Preencheu a senha");
        Browser.getCurrentDriver().findElement(By.id("SubmitLogin")).click();
        System.out.println("Clicou em sign in");
        assertTrue(Browser.getCurrentDriver().getCurrentUrl()
                .contains(Utils.getBaseUrl().concat("index.php?controller=my-account")));
        System.out.println("Validou a URL de minha conta");
        assertTrue(Browser.getCurrentDriver().findElement(By.className("page-heading")).getText().contains("MY ACCOUNT"));
        System.out.println("Validou minha conta no site");
    }

    @Test
    @Story("Acessar categoria")
    public void testAcessCategoryTShirts(){
        //Iniciar a página
        HomePage home = new HomePage();
        CategoryPage category = new CategoryPage();

        //Clicar na categoria T-Shirts
        home.clickCategoryTShirts();

        //Validar se ao clicar na categoria T-Shirts ocorre o direcionamento correto.
        assertTrue(category.isPageTshirts());
    }

    @Test
    @Story("Acessar página do produto")
    public void testAddProductToProductPage(){
        //Acessar a categoria T-Shirts
        testAcessCategoryTShirts();

        //Iniciar a spáginas
        CategoryPage category = new CategoryPage();
        ProductPage pdp = new ProductPage();

        //Salva o nome do produto na página de categoria
        String nameProductCategory = category.getProductNameCategory();

        //Clicar em More e adicionar para a página de produto
        category.clickProductAddToProductPage();

        //Verificar se o produto está na página de detalhes do produto corretamente
        assertTrue(pdp.getProductNamePDP().equals(nameProductCategory));
    }

    @Test
    public void testSearch(){
        String quest = "DRESS";
        String questResultQtd = "7 results have been found.";

        //iniciar as páginas
        HomePage home = new HomePage();
        SearchPage search = new SearchPage();

        //Fazer a pesquisa
        home.doSearch(quest);

        //Validar a pesquisa
        assertTrue(search.isSearchPage());
        assertEquals(search.getTextLighter().replace("\"", ""), quest);
        assertThat(search.getTextHeading_counter(), CoreMatchers.containsString(questResultQtd));
    }

    @Test
    @Story("Adicionar produto no carrinho")
    public void testAddProductToCartPage(){

        //Acessa a página do produto
        testAddProductToProductPage();

        //Iniciar páginas
        ProductPage pdp = new ProductPage();
        CartPage cart = new CartPage();

        //Salvar o nome do produto na página PDP
        String nameProductPDP = pdp.getProductNamePDP();

        //Clicar no botão de adicionar ao carrinho
        pdp.clickButtonAddToCart();

        //Clicar no botão Proceed to checkout do modal
        pdp.clickButtonModalProceedToCheckout();

        //Validação do nome do produto no carrinho
        assertTrue(cart.getNameProductCart().equals(nameProductPDP));
    }

    @Test
    public void testCreateAnAccount(){
        //Iniciar as páginas
        HomePage home = new HomePage();
        LoginPage loginpage = new LoginPage();
        NewAccountPage newaccountpage = new NewAccountPage();

        //Clicar no botão Sig in
        home.clickSigIn();

        //Digitar e-mail para novo cadastro
        Browser.getCurrentDriver().findElement(By.id("email_create")).sendKeys("cesarantoniazzi4@hotmail.com");
        System.out.println("Preencheu o email");

        //Clicar em Create an account
        Browser.getCurrentDriver().findElement(By.id("SubmitCreate")).click();
        System.out.println("Clicou em sign in");

        //Selecionar sexo
        Browser.getCurrentDriver().findElement(By.id("id_gender1")).click();
        System.out.println("Selecionou o sexo");

        //Preencher primeiro nome
        Browser.getCurrentDriver().findElement(By.id("customer_firstname")).sendKeys("César");
        System.out.println("Preencheu o primeiro nome");

        //Preencher sobrenome
        Browser.getCurrentDriver().findElement(By.id("customer_lastname")).sendKeys("Antoniazzi");
        System.out.println("Preencheu o Sobrenome");

        //Preencher senha
        Browser.getCurrentDriver().findElement(By.id("passwd")).sendKeys("teste@123");
        System.out.println("Preencheu a senha");

        //Preencher data de nascimento
        Browser.getCurrentDriver().findElement(By.id("days")).sendKeys("25");
        System.out.println("Preencheu dia de nascimento");
        Browser.getCurrentDriver().findElement(By.id("months")).sendKeys("April");
        System.out.println("Preencheu mês de nascimento");
        Browser.getCurrentDriver().findElement(By.id("years")).sendKeys("1992");
        System.out.println("Preencheu ano de nascimento");

        //Preenchimento company
        Browser.getCurrentDriver().findElement(By.id("company")).sendKeys("Automation House");
        System.out.println("Preencheu company");

        //Preenchimento Address
        Browser.getCurrentDriver().findElement(By.id("address1")).sendKeys("Rua Araujo Viana, 247");
        System.out.println("Preencheu Address");

        //Preenchimento Address 2 line
        Browser.getCurrentDriver().findElement(By.name("address2")).sendKeys("Escritório");
        System.out.println("Preencheu Address line 2");

        //Preenchimento City
        Browser.getCurrentDriver().findElement(By.name("city")).sendKeys("Honolulu");
        System.out.println("Preencheu City");

        //Preenchimento State
        Browser.getCurrentDriver().findElement(By.name("id_state")).sendKeys("Hawaii");
        System.out.println("Preencheu State");

        //Preenchimento Postalcode
        Browser.getCurrentDriver().findElement(By.name("postcode")).sendKeys("00001");
        System.out.println("Preencheu Postal Code");

        //Preenchimento Add Information
        Browser.getCurrentDriver().findElement(By.name("other")).sendKeys("Meu primeiro teste automatizado XD");
        System.out.println("Preencheu Add information");

        //Preenchimento Home Phone
        Browser.getCurrentDriver().findElement(By.name("phone")).sendKeys("555134900000");
        System.out.println("Preencheu Home Phone");

        //Preenchimento Mobile Phone
        Browser.getCurrentDriver().findElement(By.name("phone_mobile")).sendKeys("5551995440001");
        System.out.println("Preencheu Mobile Phone");

        //Preenchimento Future reference
        Browser.getCurrentDriver().findElement(By.name("alias")).sendKeys("Rua tests");
        System.out.println("Preencheu Future reference");

        //Clicar em Register
        Browser.getCurrentDriver().findElement(By.id("submitAccount")).click();
        System.out.println("Clicou em Register");

        //Validação da conta no site
        assertTrue(Browser.getCurrentDriver().findElement(By.className("page-heading")).getText().contains("MY ACCOUNT"));
        System.out.println("Validou minha conta no site");

        //Clicar para desfazer login
        Browser.getCurrentDriver().findElement(By.className("logout")).click();
        System.out.println("Clicou em Sign Out");

        //Validação do retorno para a tela de login
        assertTrue(Browser.getCurrentDriver().findElement(By.className("page-heading")).getText().contains("AUTHENTICATION"));
        System.out.println("Validou retorno para a página de login");

        //Faz login novamente na página
        //Preenchimento do email e senha
        Browser.getCurrentDriver().findElement(By.id("email")).sendKeys("cesarantoniazzi4@hotmail.com");
        System.out.println("Preencheu Future reference");
        Browser.getCurrentDriver().findElement(By.name("passwd")).sendKeys("teste@123");
        System.out.println("Preencheu Future reference");

        //Clicar em Sign in para acessar com meu cadastro
        Browser.getCurrentDriver().findElement(By.id("SubmitLogin")).click();
        System.out.println("Clicou em Sign in");

        //Validação do meu acesso com o cadastro no site
        assertTrue(Browser.getCurrentDriver().findElement(By.className("page-heading")).getText().contains("MY ACCOUNT"));
        System.out.println("Validou minha conta no site");

    }


}
