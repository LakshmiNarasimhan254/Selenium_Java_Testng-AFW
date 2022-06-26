var ChromeSandboxPeerDriver;
(function (ChromeSandboxPeerDriver) {
    ChromeSandboxPeerDriver.ExtensionNamespace = typeof browser != 'undefined' ? browser : chrome;
    ChromeSandboxPeerDriver.customWindow = window;
    function getTime() {
        return (window.performance) ? window.performance.now() : Date.now();
    }
    ChromeSandboxPeerDriver.getTime = getTime;
    function createNewResponse(messageId) {
        return {
            msgId: messageId,
            error: undefined,
            payload: undefined,
            performanceTiming: {
                requestStart: undefined,
                requestEnd: undefined
            }
        };
    }
    function handleMessage(request, sender, sendResponse) {
        if (request && ChromeSandboxPeerDriver.customWindow._chromeSandboxLibrary.canHandle(request.type)) {
            var response = createNewResponse(request.msgId);
            try {
                response.performanceTiming.requestStart = this.getTime();
                response.payload = ChromeSandboxPeerDriver.customWindow._chromeSandboxLibrary.handle(request.type, request.payload);
                response.performanceTiming.requestEnd = this.getTime();
            }
            catch (e) {
                response.error = (e && typeof e.toString === "function") ? e.toString() : "Error object in ChromeSandboxPeerDriver is undefined or has no toString method";
            }
            finally {
                if (response.payload && typeof response.payload.then === "function") {
                    response.payload.then(function (result) {
                        response.payload = result;
                        sendResponse(response);
                    }).catch(function (error) {
                        response.payload = null;
                        response.error = error.toString();
                        sendResponse(response);
                    });
                    return true;
                }
                else {
                    sendResponse(response);
                }
            }
        }
    }
    ChromeSandboxPeerDriver.handleMessage = handleMessage;
    function bootstrap() {
        if (!ChromeSandboxPeerDriver.customWindow) {
            ChromeSandboxPeerDriver.customWindow = window;
        }
        if (!ChromeSandboxPeerDriver.customWindow._chromeSandboxLibrary) {
            ChromeSandboxPeerDriver.customWindow._chromeSandboxLibrary = new SandboxLibrary.Sandboxer(function (msg) {
                ChromeSandboxPeerDriver.ExtensionNamespace.runtime.sendMessage({
                    type: "UBPExternalMessage.Sandbox",
                    payload: msg
                });
            });
            ChromeSandboxPeerDriver.ExtensionNamespace.runtime.onMessage.addListener(this.handleMessage.bind(this));
            window.addEventListener("message", function () {
                ChromeSandboxPeerDriver.customWindow._chromeSandboxLibrary.sandboxMessageHandler.apply(ChromeSandboxPeerDriver.customWindow._chromeSandboxLibrary, arguments);
            });
        }
    }
    ChromeSandboxPeerDriver.bootstrap = bootstrap;
    ChromeSandboxPeerDriver.bootstrap.call(ChromeSandboxPeerDriver);
})(ChromeSandboxPeerDriver || (ChromeSandboxPeerDriver = {}));
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiU2FuZGJveERyaXZlci5qcyIsInNvdXJjZVJvb3QiOiIiLCJzb3VyY2VzIjpbIi4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uL3NyYy91YnAvZXh0ZW5zaW9uL2NvbnRleHR1YWwvcGVlci1zY3JpcHRzL2RyaXZlcnMvY2hyb21lL1NhbmRib3hEcml2ZXIudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBV0EsSUFBVSx1QkFBdUIsQ0FrS2hDO0FBbEtELFdBQVUsdUJBQXVCO0lBUWxCLDBDQUFrQixHQUFRLE9BQU8sT0FBTyxJQUFJLFdBQVcsQ0FBQyxDQUFDLENBQUMsT0FBTyxDQUFDLENBQUMsQ0FBQyxNQUFNLENBQUM7SUF1QjNFLG9DQUFZLEdBQTJDLE1BQU0sQ0FBQztJQVd6RSxTQUFnQixPQUFPO1FBQ25CLE9BQU8sQ0FBQyxNQUFNLENBQUMsV0FBVyxDQUFDLENBQUMsQ0FBQyxDQUFDLE1BQU0sQ0FBQyxXQUFXLENBQUMsR0FBRyxFQUFFLENBQUMsQ0FBQyxDQUFDLElBQUksQ0FBQyxHQUFHLEVBQUUsQ0FBQztJQUN4RSxDQUFDO0lBRmUsK0JBQU8sVUFFdEIsQ0FBQTtJQVlELFNBQVMsaUJBQWlCLENBQUMsU0FBZ0I7UUFDdkMsT0FBTztZQUNILEtBQUssRUFBUSxTQUFTO1lBQ3RCLEtBQUssRUFBUSxTQUFTO1lBQ3RCLE9BQU8sRUFBTSxTQUFTO1lBQ3RCLGlCQUFpQixFQUFFO2dCQUNmLFlBQVksRUFBRSxTQUFTO2dCQUN2QixVQUFVLEVBQUksU0FBUzthQUMxQjtTQUNKLENBQUM7SUFDTixDQUFDO0lBZUQsU0FBZ0IsYUFBYSxDQUFDLE9BQThCLEVBQzlCLE1BQW9DLEVBQ3BDLFlBQXVEO1FBQ2pGLElBQUksT0FBTyxJQUFJLHVCQUF1QixDQUFDLFlBQVksQ0FBQyxxQkFBcUIsQ0FBQyxTQUFTLENBQUMsT0FBTyxDQUFDLElBQUksQ0FBQyxFQUFFO1lBRS9GLElBQUksUUFBUSxHQUEyQixpQkFBaUIsQ0FBQyxPQUFPLENBQUMsS0FBSyxDQUFDLENBQUM7WUFFeEUsSUFBSTtnQkFDQSxRQUFRLENBQUMsaUJBQWlCLENBQUMsWUFBWSxHQUFHLElBQUksQ0FBQyxPQUFPLEVBQUUsQ0FBQztnQkFJekQsUUFBUSxDQUFDLE9BQU8sR0FBRyx1QkFBdUIsQ0FBQyxZQUFZLENBQUMscUJBQXFCLENBQUMsTUFBTSxDQUFDLE9BQU8sQ0FBQyxJQUFJLEVBQUMsT0FBTyxDQUFDLE9BQU8sQ0FBQyxDQUFDO2dCQUVuSCxRQUFRLENBQUMsaUJBQWlCLENBQUMsVUFBVSxHQUFHLElBQUksQ0FBQyxPQUFPLEVBQUUsQ0FBQzthQUMxRDtZQUNELE9BQU0sQ0FBQyxFQUFFO2dCQUdMLFFBQVEsQ0FBQyxLQUFLLEdBQUcsQ0FBQyxDQUFDLElBQUksT0FBTyxDQUFDLENBQUMsUUFBUSxLQUFLLFVBQVUsQ0FBQyxDQUFBLENBQUMsQ0FBQyxDQUFDLENBQUMsUUFBUSxFQUFFLENBQUMsQ0FBQyxDQUFDLGdGQUFnRixDQUFDO2FBQzdKO29CQUNPO2dCQU1KLElBQUcsUUFBUSxDQUFDLE9BQU8sSUFBSSxPQUFPLFFBQVEsQ0FBQyxPQUFPLENBQUMsSUFBSSxLQUFLLFVBQVUsRUFBRTtvQkFDaEUsUUFBUSxDQUFDLE9BQU8sQ0FBQyxJQUFJLENBQUMsVUFBUyxNQUFNO3dCQUNqQyxRQUFRLENBQUMsT0FBTyxHQUFHLE1BQU0sQ0FBQzt3QkFDMUIsWUFBWSxDQUFDLFFBQVEsQ0FBQyxDQUFDO29CQUMzQixDQUFDLENBQUMsQ0FBQyxLQUFLLENBQUMsVUFBUyxLQUFLO3dCQUNuQixRQUFRLENBQUMsT0FBTyxHQUFHLElBQUksQ0FBQzt3QkFDeEIsUUFBUSxDQUFDLEtBQUssR0FBSSxLQUFLLENBQUMsUUFBUSxFQUFFLENBQUM7d0JBQ25DLFlBQVksQ0FBQyxRQUFRLENBQUMsQ0FBQztvQkFDM0IsQ0FBQyxDQUFDLENBQUM7b0JBS0gsT0FBTyxJQUFJLENBQUM7aUJBQ2Y7cUJBQU07b0JBQ0osWUFBWSxDQUFDLFFBQVEsQ0FBQyxDQUFDO2lCQUN6QjthQUNKO1NBQ0o7SUFDTCxDQUFDO0lBOUNlLHFDQUFhLGdCQThDNUIsQ0FBQTtJQVFELFNBQWdCLFNBQVM7UUFFckIsSUFBSSxDQUFDLHVCQUF1QixDQUFDLFlBQVksRUFBRTtZQUV2Qyx1QkFBdUIsQ0FBQyxZQUFZLEdBQXVCLE1BQU0sQ0FBQztTQUNyRTtRQUdELElBQUksQ0FBQyx1QkFBdUIsQ0FBQyxZQUFZLENBQUMscUJBQXFCLEVBQUU7WUFFN0QsdUJBQXVCLENBQUMsWUFBWSxDQUFDLHFCQUFxQixHQUFHLElBQUksY0FBYyxDQUFDLFNBQVMsQ0FBQyxVQUFVLEdBQUc7Z0JBQ25HLHdCQUFBLGtCQUFrQixDQUFDLE9BQU8sQ0FBQyxXQUFXLENBQUM7b0JBQ25DLElBQUksRUFBRSw0QkFBNEI7b0JBQ2xDLE9BQU8sRUFBRSxHQUFHO2lCQUNmLENBQUMsQ0FBQztZQUNQLENBQUMsQ0FBQyxDQUFDO1lBRUgsd0JBQUEsa0JBQWtCLENBQUMsT0FBTyxDQUFDLFNBQVMsQ0FBQyxXQUFXLENBQUMsSUFBSSxDQUFDLGFBQWEsQ0FBQyxJQUFJLENBQUMsSUFBSSxDQUFDLENBQUMsQ0FBQztZQUVoRixNQUFNLENBQUMsZ0JBQWdCLENBQUMsU0FBUyxFQUFFO2dCQUMvQix1QkFBdUIsQ0FBQyxZQUFZLENBQUMscUJBQXFCLENBQUMscUJBQXFCLENBQUMsS0FBSyxDQUFDLHVCQUF1QixDQUFDLFlBQVksQ0FBQyxxQkFBcUIsRUFBRSxTQUFTLENBQUMsQ0FBQztZQUNsSyxDQUFDLENBQUMsQ0FBQztTQUNOO0lBQ0wsQ0FBQztJQXZCZSxpQ0FBUyxZQXVCeEIsQ0FBQTtJQUdELHVCQUF1QixDQUFDLFNBQVMsQ0FBQyxJQUFJLENBQUMsdUJBQXVCLENBQUMsQ0FBQztBQUNwRSxDQUFDLEVBbEtTLHVCQUF1QixLQUF2Qix1QkFBdUIsUUFrS2hDIiwic291cmNlc0NvbnRlbnQiOlsiLyoqXG4gKiBUaGlzIGJvb3RzdHJhcHMge0BsaW5rIFNhbmRib3hMaWJyYXJ5fSB1bmRlciBDaHJvbWUncyBjb250ZW50IHNjcmlwdFxuICogZW52aXJvbm1lbnQgb25seSBPTkNFOiBUbyBhdm9pZCBkdXBsaWNhdGUgbGlzdGVuZXJzIG9uIGNocm9tZSBydW50aW1lXG4gKiBtZXNzYWdlcywge0BsaW5rIENocm9tZVNhbmRib3hQZWVyRHJpdmVyI2Jvb3RzdHJhcH0gd2lsbCBvbmx5IHJlZ2lzdGVyIHRoZSBsaXN0ZW5lciBvbmNlXG4gKiB3aXRoaW4gdGhlIGNvbnRlbnQgc2NyaXB0IEpSRSBsaWZldGltZSBieSBpZ25vcmluZyBhbGwgc3Vic2VxdWVudCBjYWxscy5cbiAqIEhvd2V2ZXIsIHtAbGluayBTYW5kYm94TGlicmFyeX0gd2lsbCBiZSByZW5ld2VkIGlmIGluamVjdGVkIGFnYWluXG4gKiB0byBlbnN1cmUgdGhlIGxpYnJhcnkgaXMgdXAgdG8gZGF0ZSBpbiBjYXNlIG9mIGV4dGVuc2lvbiB1cGRhdGVzL3Jlc3RhcnRzLlxuICpcbiAqIFhYWDogcGVlciBzY3JpcHRzIGNhbm5vdCB1c2UgdGhlIG1vZHVsZSBsb2FkZXIgYXMgdGhleSBhcmUgdG8gYmUgaW5qZWN0ZWRcbiAqIG9udG8gdGhlIHBhZ2UgZGlyZWN0bHkgYW5kIHRodXMgbmVlZCB0byBsaWdodC13ZWlnaHQuXG4gKi9cbm5hbWVzcGFjZSBDaHJvbWVTYW5kYm94UGVlckRyaXZlciB7XG4gICAgLyoqXG4gICAgICogR2V0IHRoZSBydW50aW1lIGV4dGVuc2lvbiBuYW1lc3BhY2UsIHdoaWNoIGlzIHRoZSBleHRlbnNpb24gQXBpIG5hbWVzcGFjZVxuICAgICAqIGZvciBjaHJvbWUsIGl0IGlzIGNocm9tZVxuICAgICAqIGZvciBlZGdlLCBpdCBpcyBlZGdlXG4gICAgICogSXQgd2lsbCBiZSBpZGVhbCB3ZSBjYW4gZ2V0IHRoaXMgZnJvbSBSdW50aW1tZVxuICAgICAqIEB0eXBlIHticm93c2VyfGNocm9tZX1cbiAgICAgKi9cbiAgICBleHBvcnQgdmFyIEV4dGVuc2lvbk5hbWVzcGFjZTogYW55ID0gdHlwZW9mIGJyb3dzZXIgIT0gJ3VuZGVmaW5lZCcgPyBicm93c2VyIDogY2hyb21lO1xuXG4gICAgLyoqXG4gICAgICogTmVlZGVkIHRvIHN1cHBvcnQgc3RvcmluZyBvZiBzYW5kYm94IGxpYnJhcnkgaW4gd2luZG93XG4gICAgICovXG4gICAgaW50ZXJmYWNlIEN1c3RvbUNocm9tZVdpbmRvdyBleHRlbmRzIFdpbmRvdyB7XG5cbiAgICAgICAgLyoqXG4gICAgICAgICAqIFRoZSBicm93c2VyLWFnbm9zdGljIGNvbnRleHR1YWwgcGVlciBsaWJyYXJ5IHN0b3JlZCBpbiB0aGUgd2luZG93IGZvciBzYW5kYm94IG1hbmFnZW1lbnRcbiAgICAgICAgICogSWYgbm90IGZvdW5kIGluIHRoZSB3aW5kb3csIGNvbnN0cnVjdCB0aGUgbGlicmFyeSwgc3RvcmUsIGFuZCByZXR1cm4gaXRcbiAgICAgICAgICpcbiAgICAgICAgICogTm90ZSB0aGF0IGFmdGVyIHNoYWRvd0RPTSBpbXBsZW1lbnRhdGlvbiwgZWFjaCBzYW5kYm94TGlicmFyeSBub3cgc3RvcmVzIHRoZSBzYW5kYm94ZXMgaW4gbWVtb3J5XG4gICAgICAgICAqIFdlIHNob3VsZCBvbmx5IHJlY29uc3RydWN0IHRoZSBzYW5kYm94IGxpYnJhcnkgd2hlbiB0aGUgd2luZG93IGVudmlyb25tZW50IGlzIHJlZnJlc2hlZCAoaGFyZC1wYWdlIHR1cm4pXG4gICAgICAgICAqIFNlZSBtb3JlIGRldGFpbHMgaGVyZTogaHR0cHM6Ly9zaW0uYW1hem9uLmNvbS9pc3N1ZXMvQUEtNTI3MFxuICAgICAgICAgKlxuICAgICAgICAgKiBAdHlwZSB7U2FuZGJveExpYnJhcnkuU2FuZGJveGVyfVxuICAgICAgICAgKi9cbiAgICAgICAgX2Nocm9tZVNhbmRib3hMaWJyYXJ5PzogU2FuZGJveExpYnJhcnkuU2FuZGJveGVyO1xuICAgIH1cblxuICAgIC8qKlxuICAgICAqIHtDdXN0b21DaHJvbWVXaW5kb3d9IHJlc3BvbnNpYmxlIGZvciBzdG9yaW5nIGFuZCByZXRyaWV2aW5nIHRoZSBzYW5kYm94TGlicmFyeVxuICAgICAqL1xuICAgIGV4cG9ydCB2YXIgY3VzdG9tV2luZG93OiBDdXN0b21DaHJvbWVXaW5kb3cgPSA8Q3VzdG9tQ2hyb21lV2luZG93PndpbmRvdztcblxuICAgIC8qKlxuICAgICAqIFJldHVybiBjdXJyZW50IHRpbWUgc3RhbXAgZm9yIHByb2ZpbGluZ1xuICAgICAqXG4gICAgICogcGVyZm9ybWFuY2Uubm93KCkgcmV0dXJucyBhIGhpZ2ggcmVzb2x1dGlvbiB0aW1lIHN0YW1wIGluIG1pbGxpb25cbiAgICAgKiBzZWNvbmRzLiBVbmxpa2UgRGF0ZS5ub3coKSwgaXQgcmV0dXJucyBhIGZsb2F0aW5nIHBvaW50IG51bWJlciB3aXRoIHVwXG4gICAgICogdG8gbWljcm9zZWNvbmQgcHJlY2lzaW9uLlxuICAgICAqIGh0dHBzOi8vZGV2ZWxvcGVyLm1vemlsbGEub3JnL2VuLVVTL2RvY3MvV2ViL0FQSS9QZXJmb3JtYW5jZS5ub3dcbiAgICAgKiBAcmV0dXJucyB7bnVtYmVyfVxuICAgICAqL1xuICAgIGV4cG9ydCBmdW5jdGlvbiBnZXRUaW1lKCkge1xuICAgICAgICByZXR1cm4gKHdpbmRvdy5wZXJmb3JtYW5jZSkgPyB3aW5kb3cucGVyZm9ybWFuY2Uubm93KCkgOiBEYXRlLm5vdygpO1xuICAgIH1cblxuICAgIC8qKlxuICAgICAqIEJ1aWxkIGFuZCByZXR1cm4gYW4gZW1wdHkge0BsaW5rIElDb250ZXh0dWFsUGVlclJlc3BvbnNlfSBvYmplY3RcbiAgICAgKlxuICAgICAqIE5vdGU6IHBlcmZvcm1hbmNlVGltaW5nIGlzIGEgcGxhY2UgaG9sZGVyIGZvciBzdG9yaW5nIHByb2ZpbGluZ1xuICAgICAqIGRhdGEgb2YgdGhlIGV4ZWN1dGlvbiAoZWFjaCBzdGFydC9lbmQgcGFpciByZWNvcmRzIHRoZSB0aW1lIHN0YW1wc1xuICAgICAqIGJlZm9yZS9hZnRlciByZXF1ZXN0IGV4ZWN1dGlvbilcbiAgICAgKlxuICAgICAqIEBwYXJhbSBtZXNzYWdlSWRcbiAgICAgKiBAcmV0dXJucyB7e21zZ0lkOiBzdHJpbmcsIGVycm9yOiB1bmRlZmluZWQsIHBheWxvYWQ6IHVuZGVmaW5lZCwgcGVyZm9ybWFuY2VUaW1pbmc6IHtyZXF1ZXN0U3RhcnQ6IHVuZGVmaW5lZCwgcmVxdWVzdEVuZDogdW5kZWZpbmVkfX19XG4gICAgICovXG4gICAgZnVuY3Rpb24gY3JlYXRlTmV3UmVzcG9uc2UobWVzc2FnZUlkOnN0cmluZyk6SUNvbnRleHR1YWxQZWVyUmVzcG9uc2Uge1xuICAgICAgICByZXR1cm4ge1xuICAgICAgICAgICAgbXNnSWQgICAgICA6IG1lc3NhZ2VJZCxcbiAgICAgICAgICAgIGVycm9yICAgICAgOiB1bmRlZmluZWQsXG4gICAgICAgICAgICBwYXlsb2FkICAgIDogdW5kZWZpbmVkLFxuICAgICAgICAgICAgcGVyZm9ybWFuY2VUaW1pbmc6IHtcbiAgICAgICAgICAgICAgICByZXF1ZXN0U3RhcnQ6IHVuZGVmaW5lZCxcbiAgICAgICAgICAgICAgICByZXF1ZXN0RW5kICA6IHVuZGVmaW5lZFxuICAgICAgICAgICAgfVxuICAgICAgICB9O1xuICAgIH1cblxuICAgIC8qKlxuICAgICAqIENhbGxlZCB3aGVuIHJlY2VpdmVkIGEgcnVudGltZSBtZXNzYWdlIGZyb20gdGhlIGV4dGVuc2lvbi5cbiAgICAgKiBGaXJzdCBjaGVjayBpZiB0aGUgbGlicmFyeSBjYW4gaGFuZGxlIHRoZSBtZXNzYWdlL3JlcXVlc3QgYW5kIG9ubHkgZm9yd2FyZFxuICAgICAqIGl0IHRvIHRoZSBsaWJyYXJ5IGlmIHllcy4gSXQgbG9ncyB0aGUgdGltZSB0b29rIGZvciB0aGUgbGlicmFyeSB0b1xuICAgICAqIGV4ZWN1dGUgdGhlIHJlcXVlc3QgYW5kIG9uY2UgaXQgaXMgZG9uZSwgc2VuZCB0aGUgcmVzdWx0IGJhY2tcbiAgICAgKiB0byB0aGUgZXh0ZW5zaW9uIGJ5IGV4ZWN1dGluZyB0aGUgY2FsbGJhY2sgcHJvdmlkZWQgYnkgcnVudGltZVxuICAgICAqXG4gICAgICogTm90ZTogXCJ0aGlzXCIgaXMgc3VwcG9zZSB0byBiaW5kIHRvIHRoZSBtb2R1bGUgbmFtZXNwYWNlXG4gICAgICpcbiAgICAgKiBAcGFyYW0gcmVxdWVzdFxuICAgICAqIEBwYXJhbSBzZW5kZXJcbiAgICAgKiBAcGFyYW0gc2VuZFJlc3BvbnNlXG4gICAgICovXG4gICAgZXhwb3J0IGZ1bmN0aW9uIGhhbmRsZU1lc3NhZ2UocmVxdWVzdDpJQ29udGV4dHVhbFBlZXJSZXF1ZXN0LFxuICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIHNlbmRlcjogY2hyb21lLnJ1bnRpbWUuTWVzc2FnZVNlbmRlcixcbiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBzZW5kUmVzcG9uc2U6KHJlc3BvbnNlOklDb250ZXh0dWFsUGVlclJlc3BvbnNlKSA9PiB2b2lkKTp2b2lkfGJvb2xlYW4ge1xuICAgICAgICBpZiAocmVxdWVzdCAmJiBDaHJvbWVTYW5kYm94UGVlckRyaXZlci5jdXN0b21XaW5kb3cuX2Nocm9tZVNhbmRib3hMaWJyYXJ5LmNhbkhhbmRsZShyZXF1ZXN0LnR5cGUpKSB7XG5cbiAgICAgICAgICAgIHZhciByZXNwb25zZTpJQ29udGV4dHVhbFBlZXJSZXNwb25zZSA9IGNyZWF0ZU5ld1Jlc3BvbnNlKHJlcXVlc3QubXNnSWQpO1xuXG4gICAgICAgICAgICB0cnkge1xuICAgICAgICAgICAgICAgIHJlc3BvbnNlLnBlcmZvcm1hbmNlVGltaW5nLnJlcXVlc3RTdGFydCA9IHRoaXMuZ2V0VGltZSgpO1xuXG4gICAgICAgICAgICAgICAgLy8gVE9ETzogYWxsb3cgdG8gYWNjZXB0IGFuIGFycmF5IG9mIElDb250ZXh0dWFsUGVlclJlcXVlc3RcbiAgICAgICAgICAgICAgICAvLyBhbmQgcmV0dXJuIGFuIGFycmF5IG9mIHJlc3BvbnNlcyBmcm9tIGFsbCByZXF1ZXN0XG4gICAgICAgICAgICAgICAgcmVzcG9uc2UucGF5bG9hZCA9IENocm9tZVNhbmRib3hQZWVyRHJpdmVyLmN1c3RvbVdpbmRvdy5fY2hyb21lU2FuZGJveExpYnJhcnkuaGFuZGxlKHJlcXVlc3QudHlwZSxyZXF1ZXN0LnBheWxvYWQpO1xuXG4gICAgICAgICAgICAgICAgcmVzcG9uc2UucGVyZm9ybWFuY2VUaW1pbmcucmVxdWVzdEVuZCA9IHRoaXMuZ2V0VGltZSgpO1xuICAgICAgICAgICAgfVxuICAgICAgICAgICAgY2F0Y2goZSkge1xuICAgICAgICAgICAgICAgIC8vIFBhc3Npbmcgd2hvbGUgZXJyb3Igb2JqZWN0IHRvIGV4dGVuc2lvbiByZXN1bHRzIGluIGVtcHR5IG9iamVjdCBpbiB0aGUgZXh0ZW5zaW9uLlxuICAgICAgICAgICAgICAgIC8vIFNvIG9ubHkgcGFzcyB0aGUgZXJyb3IgbWVzc2FnZS5cbiAgICAgICAgICAgICAgICByZXNwb25zZS5lcnJvciA9IChlICYmIHR5cGVvZiBlLnRvU3RyaW5nID09PSBcImZ1bmN0aW9uXCIpPyBlLnRvU3RyaW5nKCkgOiBcIkVycm9yIG9iamVjdCBpbiBDaHJvbWVTYW5kYm94UGVlckRyaXZlciBpcyB1bmRlZmluZWQgb3IgaGFzIG5vIHRvU3RyaW5nIG1ldGhvZFwiO1xuICAgICAgICAgICAgfVxuICAgICAgICAgICAgZmluYWxseSB7XG4gICAgICAgICAgICAgICAgLyoqXG4gICAgICAgICAgICAgICAgICogV2hpbGUgc2VuZGluZyBhc3luY2hyb25vdXMgcmVzcG9uc2VzLCBDaHJvbWVTYW5kYm94UGVlckRyaXZlci5nZXRTYW5kYm94TGlicmFyeSgpLmhhbmRsZSB3aWxsIHJldHVybiBhIHByb21pc2UuIEJlbG93IGNoZWNrXG4gICAgICAgICAgICAgICAgICogbWFrZXMgc3VyZSBpZiB0aGUgcmV0dXJuIHZhbHVlIGlzIGEgcHJvbWlzZSwgaXQgd2FpdHMgdGlsbCBpdCBnZXRzIHJlc29sdmVkIGFuZCBzZW5kcyB0aGVcbiAgICAgICAgICAgICAgICAgKiByZXN1bHQgb2YgcHJvbWlzZSBhcyByZXNwb25zZSdzIHBheWxvYWQuXG4gICAgICAgICAgICAgICAgICovXG4gICAgICAgICAgICAgICAgaWYocmVzcG9uc2UucGF5bG9hZCAmJiB0eXBlb2YgcmVzcG9uc2UucGF5bG9hZC50aGVuID09PSBcImZ1bmN0aW9uXCIpIHtcbiAgICAgICAgICAgICAgICAgICAgcmVzcG9uc2UucGF5bG9hZC50aGVuKGZ1bmN0aW9uKHJlc3VsdCl7XG4gICAgICAgICAgICAgICAgICAgICAgICByZXNwb25zZS5wYXlsb2FkID0gcmVzdWx0O1xuICAgICAgICAgICAgICAgICAgICAgICAgc2VuZFJlc3BvbnNlKHJlc3BvbnNlKTtcbiAgICAgICAgICAgICAgICAgICAgfSkuY2F0Y2goZnVuY3Rpb24oZXJyb3Ipe1xuICAgICAgICAgICAgICAgICAgICAgICAgcmVzcG9uc2UucGF5bG9hZCA9IG51bGw7XG4gICAgICAgICAgICAgICAgICAgICAgICByZXNwb25zZS5lcnJvciA9ICBlcnJvci50b1N0cmluZygpO1xuICAgICAgICAgICAgICAgICAgICAgICAgc2VuZFJlc3BvbnNlKHJlc3BvbnNlKTtcbiAgICAgICAgICAgICAgICAgICAgfSk7XG4gICAgICAgICAgICAgICAgICAgIC8qKlxuICAgICAgICAgICAgICAgICAgICAgKiBSZXR1cm5pbmcgJ3RydWUnIGluZGljYXRlcyBpdHMgYW4gYXN5bmNocm9ub3VzIHJlc3BvbnNlXG4gICAgICAgICAgICAgICAgICAgICAqIGh0dHBzOi8vZGV2ZWxvcGVyLm1vemlsbGEub3JnL2VuLVVTL0FkZC1vbnMvV2ViRXh0ZW5zaW9ucy9BUEkvcnVudGltZS9vbk1lc3NhZ2UjU2VuZGluZ19hbl9hc3luY2hyb25vdXNfcmVzcG9uc2VfdXNpbmdfc2VuZFJlc3BvbnNlXG4gICAgICAgICAgICAgICAgICAgICAqL1xuICAgICAgICAgICAgICAgICAgICByZXR1cm4gdHJ1ZTsgXG4gICAgICAgICAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgICAgICAgICBzZW5kUmVzcG9uc2UocmVzcG9uc2UpO1xuICAgICAgICAgICAgICAgIH1cbiAgICAgICAgICAgIH1cbiAgICAgICAgfVxuICAgIH1cblxuICAgIC8qKlxuICAgICAqIEJvb3RzdHJhcHMgdGhlIGNvbnRleHR1YWwgcGVlciBsaWJyYXJ5IHRvIHRoZSBjb250ZXh0IHNjcmlwdCBydW50aW1lXG4gICAgICpcbiAgICAgKiBOb3RlOiBcInRoaXNcIiBpcyBzdXBwb3NlIHRvIGJpbmQgdG8gdGhlIG1vZHVsZSBuYW1lc3BhY2VcbiAgICAgKlxuICAgICAqL1xuICAgIGV4cG9ydCBmdW5jdGlvbiBib290c3RyYXAoKSB7XG5cbiAgICAgICAgaWYgKCFDaHJvbWVTYW5kYm94UGVlckRyaXZlci5jdXN0b21XaW5kb3cpIHtcbiAgICAgICAgICAgIC8vIE5vdCBuZWVkZWQgYW5kIHNob3VsZCBuZXZlciBiZSBjYWxsZWQsIGJ1dCBhZGRlZCBhcyBzYWZldHkgY2hlY2tcbiAgICAgICAgICAgIENocm9tZVNhbmRib3hQZWVyRHJpdmVyLmN1c3RvbVdpbmRvdyA9IDxDdXN0b21DaHJvbWVXaW5kb3c+d2luZG93O1xuICAgICAgICB9XG5cbiAgICAgICAgLy8gQWxsb3cgYm9vdHN0cmFwIHRvIGhhcHBlbiBvbmx5IG9uY2UgcGVyIEpSRVxuICAgICAgICBpZiAoIUNocm9tZVNhbmRib3hQZWVyRHJpdmVyLmN1c3RvbVdpbmRvdy5fY2hyb21lU2FuZGJveExpYnJhcnkpIHtcblxuICAgICAgICAgICAgQ2hyb21lU2FuZGJveFBlZXJEcml2ZXIuY3VzdG9tV2luZG93Ll9jaHJvbWVTYW5kYm94TGlicmFyeSA9IG5ldyBTYW5kYm94TGlicmFyeS5TYW5kYm94ZXIoZnVuY3Rpb24gKG1zZykge1xuICAgICAgICAgICAgICAgIEV4dGVuc2lvbk5hbWVzcGFjZS5ydW50aW1lLnNlbmRNZXNzYWdlKHtcbiAgICAgICAgICAgICAgICAgICAgdHlwZTogXCJVQlBFeHRlcm5hbE1lc3NhZ2UuU2FuZGJveFwiLFxuICAgICAgICAgICAgICAgICAgICBwYXlsb2FkOiBtc2dcbiAgICAgICAgICAgICAgICB9KTtcbiAgICAgICAgICAgIH0pO1xuXG4gICAgICAgICAgICBFeHRlbnNpb25OYW1lc3BhY2UucnVudGltZS5vbk1lc3NhZ2UuYWRkTGlzdGVuZXIodGhpcy5oYW5kbGVNZXNzYWdlLmJpbmQodGhpcykpO1xuXG4gICAgICAgICAgICB3aW5kb3cuYWRkRXZlbnRMaXN0ZW5lcihcIm1lc3NhZ2VcIiwgZnVuY3Rpb24gKCkge1xuICAgICAgICAgICAgICAgIENocm9tZVNhbmRib3hQZWVyRHJpdmVyLmN1c3RvbVdpbmRvdy5fY2hyb21lU2FuZGJveExpYnJhcnkuc2FuZGJveE1lc3NhZ2VIYW5kbGVyLmFwcGx5KENocm9tZVNhbmRib3hQZWVyRHJpdmVyLmN1c3RvbVdpbmRvdy5fY2hyb21lU2FuZGJveExpYnJhcnksIGFyZ3VtZW50cyk7XG4gICAgICAgICAgICB9KTtcbiAgICAgICAgfVxuICAgIH1cblxuICAgIC8vIGNhbGwgZXZlcnkgdGltZSB0aGUgZHJpdmVyIGlzIGluamVjdGVkIGJ1dCBvbmx5IHRoZSBmaXJzdCB3aWxsIHN1Y2NlZWRcbiAgICBDaHJvbWVTYW5kYm94UGVlckRyaXZlci5ib290c3RyYXAuY2FsbChDaHJvbWVTYW5kYm94UGVlckRyaXZlcik7XG59XG4iXX0=