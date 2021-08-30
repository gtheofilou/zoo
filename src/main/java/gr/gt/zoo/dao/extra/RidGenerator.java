package gr.gt.zoo.dao.extra;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * RidGenerator provides a thread safe way to generate row ids
 */
@Service
public class RidGenerator {

    public static final String SUFFIX = "id";

    private AtomicLong rid = new AtomicLong();

    public String nextRid() {
        return SUFFIX + rid.addAndGet(1);
    }
}
