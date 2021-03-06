package com.jfilter.filter;

import com.jfilter.components.DynamicFilterProvider;
import com.jfilter.request.RequestSession;
import org.springframework.core.MethodParameter;

/**
 * Dynamic filter event
 *
 * <p>This event calls when {@link DynamicFilterProvider} attempts to
 * get {@link FilterFields} from inherited class
 */
public interface DynamicFilterEvent {
    FilterFields onGetFilterFields(MethodParameter methodParameter, RequestSession request);
}
