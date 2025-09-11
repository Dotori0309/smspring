package edu.sm.app.service;

import com.github.pagehelper.Page;
import edu.sm.app.dto.Cust;
import edu.sm.app.dto.CustSearch;
import edu.sm.app.repository.CustRepository;
import edu.sm.common.frame.SmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustService implements SmService<Cust, String> {

    final CustRepository custRepository;
    private static final int DEFAULT_PAGE_SIZE = 10;


    @Override
    public void register(Cust cust) throws Exception {
        custRepository.insert(cust);
    }

    @Override
    public void modify(Cust cust) throws Exception {
        custRepository.update(cust);
    }

    @Override
    public void remove(String s) throws Exception {
        custRepository.delete(s);
    }

    @Override
    public List<Cust> get() throws Exception {
        return custRepository.selectAll();
    }

    @Override
    public Cust get(String s) throws Exception {
        return custRepository.select(s);
    }

    public Page<Cust> getpage() throws Exception {
        return custRepository.getpage();
    }

    public List<Cust> search(CustSearch cs) throws Exception {
        return custRepository.searchCustList(cs);
    }

    /** 컨트롤러가 기대하는 시그니처 맞추기 */
    public List<Cust> searchCustList(CustSearch custSearch) throws Exception {
        return custRepository.searchCustList(custSearch);
    }

    /** 컨트롤러가 기대하는 시그니처 맞추기 (pageNo 사용) */
    public Page<Cust> getPage(int pageNo) throws Exception {
        // PageHelper로 페이징 시작
        com.github.pagehelper.PageHelper.startPage(pageNo, DEFAULT_PAGE_SIZE);
        // 단순 목록 쿼리 실행 (SELECT * FROM cust)
        return custRepository.getpage();
    }
}