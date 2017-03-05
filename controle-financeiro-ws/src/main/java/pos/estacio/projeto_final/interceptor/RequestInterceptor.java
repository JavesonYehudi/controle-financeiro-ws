package pos.estacio.projeto_final.interceptor;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import pos.estacio.projeto_final.filter.UserFilter;
import pos.estacio.projeto_final.resource.UserResource;

public class RequestInterceptor implements DynamicFeature{

	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		if (!UserResource.class.equals(resourceInfo.getResourceClass())){
                context.register(UserFilter.class);
        }
	}

}
