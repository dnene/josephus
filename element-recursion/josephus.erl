-module(josephus).
-export([benchmark/0]).
-compile(native).

shout(Count,Nth) ->
    shout(lists:seq(1,Count),[],Nth,1).

shout([Head | []], [], _, _) -> Head;
shout([], Survivors, Nth, Counter) -> 
    %% io:format("Reversing~n",[]),
    shout(lists:reverse(Survivors),[], Nth, Counter);
shout([_ | Tail], Survivors, Nth, 1) ->
    %% io:format("~w dies~n",[Head]),
    shout(Tail, Survivors, Nth, 2);
shout([Head | Tail], Survivors, Nth, Nth) ->
    %% io:format("~w survives~n",[Head]),
    shout(Tail, [Head | Survivors], Nth, 1);
shout([Head | Tail], Survivors, Nth, Counter) ->
    %% io:format("~w survives~n",[Head]),
    shout(Tail, [Head | Survivors], Nth, Counter + 1).

run_iterations(0) -> ok;
run_iterations(Iterations) ->
    shout(40,3),
    run_iterations(Iterations - 1).

run_times(_,0) -> ok;
run_times(Iterations, Times) ->
    Start = os:timestamp(),
    run_iterations(Iterations),
    End = os:timestamp(),
    io:format("~w~n",[timer:now_diff(End,Start) / Iterations]),
    run_times(Iterations,Times-1).

benchmark() ->
    Iter = 1000000,
    io:format("(element recursive)~n",[]),
    run_times(Iter,10).
