package com.example.company.company;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    /* Modifying: Enables repository methods annotated with @Query to
     execute SQL or JPQL statements that modify data, such as
     INSERT, UPDATE, DELETE, or DDL (Data Definition Language) statements
     By default, Spring Data JPA treats repository queries as read-only, so we need this*/

    /* Transactional : Specifies that a method or class should be executed within a database transaction
    * Ensures that all operations within the method are completed successfully as a single unit of work.
    *  If any operation fails, all changes are rolled back to maintain data integrity*/
    @Modifying
    @Transactional
    @Query(value="INSERT INTO Company(companyName,companyDescription) VALUES (:companyName,:companyDescription)")
    public void insertCompany(@Param("companyName") String name, @Param("companyDescription") String companyDescription);

    @Query(value="SELECT * from Company where company.companyid= :companyId",nativeQuery = true)
    public Company findCompanyByIdNative(@Param("companyId") Long companyId);
}
