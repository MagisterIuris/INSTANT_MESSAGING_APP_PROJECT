package fr.mightycode.cpoo.server.controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import fr.mightycode.cpoo.server.service.SSEService;

@RestController
@RequestMapping("/sse")
public class SSEController {
  private final SSEService sseService;
  public SSEController(SSEService sseService) {
    this.sseService = sseService;
  }
  @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public SseEmitter subscribeToSSEEvents() {
    return sseService.subscribe();
  }
}
