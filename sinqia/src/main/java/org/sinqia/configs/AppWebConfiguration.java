package org.sinqia.configs;

import org.sinqia.controllers.EmpresaController;
import org.sinqia.controllers.EventoController;
import org.sinqia.controllers.UsuarioController;
import org.sinqia.daos.CategoriaDao;
import org.sinqia.daos.EmpresaDao;
import org.sinqia.daos.EventoDao;
import org.sinqia.daos.UsuarioDao;
import org.sinqia.daos.enderecoDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan(basePackageClasses= {EmpresaController.class, 
									EventoController.class,
									UsuarioController.class,
									EmpresaDao.class, 
									EventoDao.class, 
									UsuarioDao.class,
									enderecoDao.class,
									CategoriaDao.class})
public class AppWebConfiguration {
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);
		
		return conversionService;
	}
}
