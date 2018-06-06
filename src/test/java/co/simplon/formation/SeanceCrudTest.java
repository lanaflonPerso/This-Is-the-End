package co.simplon.formation;

import co.simplon.formation.modele.Seance;
import co.simplon.formation.repository.SeanceRepository;
import co.simplon.formation.service.SeanceService;
import org.junit.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



public class SeanceCrudTest {

    @Mock
    private SeanceRepository repo;

    @InjectMocks
    private SeanceService service;

    private Seance s1 = new Seance();
    private Seance s2 = new Seance();
    private Seance s3 = new Seance();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        s1.setId(1L);
        s1.setNom("test1");
        s2.setId(2L);
        s2.setNom("test2");
        s3.setId(3L);
        s3.setNom("test3");
    }

    @Test
    public void testGetAll() throws Exception {
        given(repo.findAll()).willReturn(Arrays.asList(s1, s2, s3));
        List<Seance> result = (List<Seance>)  service.findAll();
        assertThat(result).hasSize(3);
        assertSeanceFields(result.get(0));
        verify(repo, times(1)).findAll();
    }

    @Test
    public void testGetById() throws Exception {
        given(repo.findById(1L)).willReturn(Optional.of(s1));
        Optional<Seance> result = service.findById(1L);
        assertThat(result.isPresent()).isTrue();
        assertSeanceFields(result.orElseGet(null));
        verify(repo, times(1)).findById(1L);
    }


    @Test
    public void testInsert() throws Exception {
        given(repo.save(s1)).willReturn(s1);
        Seance result = service.save(s1);
        assertSeanceFields(result);
        verify(repo, times(1)).save(s1);
    }


    //verifier si l'objet seance s1 a les mêmes champs
    private void assertSeanceFields(Seance seance) {
        assertThat(seance.getId()).isInstanceOf(Long.class);
        assertThat(seance.getId()).isEqualTo(1);
        assertThat(seance.getNom()).isEqualTo("test1");

    }
}