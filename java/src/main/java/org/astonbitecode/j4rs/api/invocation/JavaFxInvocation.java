/*
 * Copyright 2020 astonbitecode
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.astonbitecode.j4rs.api.invocation;

import javafx.application.Platform;
import org.astonbitecode.j4rs.api.Instance;
import org.astonbitecode.j4rs.api.dtos.InvocationArg;

public class JavaFxInvocation<T> implements Instance<T> {
    private JsonInvocationImpl<T> jsonInvocation;

    public JavaFxInvocation(JsonInvocationImpl<T> jsonInvocation) {
        this.jsonInvocation = jsonInvocation;
    }

    @Override
    public Instance invoke(String methodName, InvocationArg... args) {
        return jsonInvocation.invoke(methodName, args);
    }

    @Override
    public Instance invokeStatic(String methodName, InvocationArg... args) {
        return jsonInvocation;
    }

    @Override
    public void invokeAsync(long functionPointerAddress, String methodName, InvocationArg... args) {
        Platform.runLater(() -> this.invoke(methodName, args));
    }

    @Override
    public void invokeToChannel(long channelAddress, String methodName, InvocationArg... args) {
        Platform.runLater(() -> this.invoke(methodName, args));
    }

    @Override
    public void initializeCallbackChannel(long channelAddress) {
        jsonInvocation.initializeCallbackChannel(channelAddress);
    }

    @Override
    public Instance field(String fieldName) {
        return jsonInvocation.field(fieldName);
    }

    @Override
    public String getJson() {
        return jsonInvocation.getJson();
    }

    @Override
    public Object getObject() {
        return jsonInvocation.getObject();
    }

    @Override
    public Class<?> getObjectClass() {
        return jsonInvocation.getObjectClass();
    }
}