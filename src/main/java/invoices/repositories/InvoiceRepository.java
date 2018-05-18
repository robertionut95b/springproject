package invoices.repositories;

import invoices.objects.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> , CrudRepository<Invoice, Long> {
    Invoice findByCompany(String company);
}
