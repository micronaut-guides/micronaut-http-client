package example.micronaut;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;

@Filter("/api/${bintray.apiversion}/repos/**") // <1>
@Requires(property = "bintray.username") // <2>
@Requires(property = "bintray.token") // <2>
public class BintrayFilter  implements HttpClientFilter {

    private final String username;
    private final String token;

    public BintrayFilter(@Value("${bintray.username}") String username,  // <3>
                         @Value("${bintray.token}") String token ) {
        this.username = username;
        this.token = token;
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        return chain.proceed(request.basicAuth(username, token)); // <4>
    }
}
