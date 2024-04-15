package com.codeUrjc.daw.Controllers.Rest;

import com.codeUrjc.daw.Model.Comment;
import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.Ticket;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.Service.CommentService;
import com.codeUrjc.daw.Service.EventService;
import com.codeUrjc.daw.Service.UserService;
import com.codeUrjc.daw.repository.CommentRepository;
import com.codeUrjc.daw.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the comments", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comments not found", content = @Content)
    })

    @GetMapping("/")
    public Collection<Comment> getComments(){
        return commentService.findAll();
    }

    @Operation(summary = "Get a comment by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the comment", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable long id){
        Optional<Comment> optionalComment = commentService.findById(id);
        if(optionalComment.isPresent()){
            Comment comment = optionalComment.get();
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Post a new comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not created", content = @Content)
    })

    @PostMapping("/")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
        commentService.save(comment);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(comment.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", location.toString()).body(comment);
    }

    @Operation(summary = "Put a comment by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not update", content = @Content)
    })

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable long id, @RequestBody Comment updateComment) {
        if (updateComment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (commentService.exist(id)) {
            updateComment.setId(id);
            commentService.save(updateComment);
            return new ResponseEntity<>(updateComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a comment by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not deleted", content = @Content)
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable long id) {
        try{
            commentService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get comments by event id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the comments for the event", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comments for the event not found", content = @Content)
    })

    @GetMapping("/event/{eventId}")
    public ResponseEntity<Collection<Comment>> getCommentsByEventId(@PathVariable long eventId){
        Collection<Comment> comments = commentRepository.findByEventId(eventId);
        if(comments != null && !comments.isEmpty()){
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get comments by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the comments by the user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comments by the user not found", content = @Content)
    })

    @GetMapping("/user/{userId}")
    public ResponseEntity<Collection<Comment>> getCommentsByUserId(@PathVariable long userId){
        Collection<Comment> comments = commentRepository.findByUserId(userId);
        if(comments != null && !comments.isEmpty()){
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a comment for a specific event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created for the event", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
    })

    @PostMapping("/event/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Comment> createCommentForEvent(@PathVariable long eventId, HttpServletRequest request, @RequestBody Comment commentRequest){
        Optional<Event> optionalEvent = eventService.findById(eventId);
        String currentUserNickname = request.getUserPrincipal().getName();
        Optional<User> optionalUser = userRepository.findByNICK(currentUserNickname);

        if (!optionalEvent.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Event event = optionalEvent.get();
        User user = optionalUser.get();

        Comment comment = new Comment(commentRequest.getDescription(), commentRequest.getNick());
        comment.setEvent(event);
        comment.setUser(user);

        commentService.save(comment);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }


}