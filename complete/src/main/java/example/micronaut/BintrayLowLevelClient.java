package example.micronaut;

import io.micronaut.context.annotation.Value;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.Client;
import io.micronaut.http.client.RxHttpClient;
import io.reactivex.Flowable;
import javax.inject.Singleton;
import java.util.List;

@Singleton // <1>
public class BintrayLowLevelClient {

    private final String bintrayApiVersion;

    private final String bintrayOrganization;

    private final String bintrayRepository;

    private final RxHttpClient httpClient;

    public BintrayLowLevelClient(@Client(BintrayApi.BINTRAY_API_URL) RxHttpClient httpClient,  // <2>
                                 @Value("${bintray.apiversion}") String bintrayApiVersion,  // <3>
                                 @Value("${bintray.organization}") String bintrayOrganization,  // <3>
                                 @Value("${bintray.repository}") String bintrayRepository) {  // <3>
        this.httpClient = httpClient;
        this.bintrayApiVersion = bintrayApiVersion;
        this.bintrayOrganization = bintrayOrganization;
        this.bintrayRepository = bintrayRepository;
    }

    Flowable<HttpResponse<List<BintrayPackage>>> fetchPackages() {
        HttpRequest req = HttpRequest.GET(packagesPath());  // <4>
        return (Flowable<HttpResponse<List<BintrayPackage>>>) httpClient.exchange(req, Argument.of(List.class, BintrayPackage.class));  // <5>
    }

    private String packagesPath() {
        StringBuilder sb = new StringBuilder();
        sb.append("/api/");
        sb.append(bintrayApiVersion);
        sb.append("/repos/");
        sb.append(bintrayOrganization);
        sb.append("/");
        sb.append(bintrayRepository);
        sb.append("/packages");
        return sb.toString();
    }
}
