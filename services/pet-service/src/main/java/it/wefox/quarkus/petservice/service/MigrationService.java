package it.wefox.quarkus.petservice.service;

import io.quarkus.liquibase.mongodb.LiquibaseMongodbFactory;
import liquibase.Liquibase;
import liquibase.changelog.ChangeSetStatus;
import liquibase.exception.LiquibaseException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 14/12/22
 * @Time: 00:39
 */

@ApplicationScoped
public class MigrationService {
    // You can Inject the object if you want to use it manually
    @Inject
    LiquibaseMongodbFactory liquibaseMongodbFactory;

    public void checkMigration() {
        // Use the liquibase instance manually
        try (Liquibase liquibase = liquibaseMongodbFactory.createLiquibase()) {
            liquibase.dropAll();
            liquibase.update(liquibaseMongodbFactory.createContexts(), liquibaseMongodbFactory.createLabels());
            // Get the list of liquibase change set statuses
            List<ChangeSetStatus> status = liquibase.getChangeSetStatuses(liquibaseMongodbFactory.createContexts(), liquibaseMongodbFactory.createLabels());
        } catch (LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }
}