package com.automateddocsys.PMADataTransfer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.automateddocsys.pma.webdata.config.PMAWebDataConfiguration;
import com.automateddocsys.pmadata.config.PMADataDataConfiguration;

@Configuration
@Import({PMAWebDataConfiguration.class, PMADataDataConfiguration.class })
@ComponentScan(basePackages={"com.automateddocsys.PMADataTransfer"})
public class DataTransferConfig {

}
