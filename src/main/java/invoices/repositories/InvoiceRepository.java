package invoices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.streetshirt.soap.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByCompany(String company);
}
