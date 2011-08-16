-module(josephus).
-export([benchmark/0]).
-compile(native).

shout(Count,Nth) ->
    shout(lists:seq(1,Count),[],Nth,1).

shout([Head | []], [], _, _) -> Head;
shout([], Survivors, Nth, Counter) ->
    %% io:format("Reversing~n",[]),
    shout(lists:reverse(Survivors),[], Nth, Counter);
shout([Head | Tail], Survivors, Nth, 1) ->
    %% io:format("~w dies~n",[Head]),
    shout(Tail, Survivors, Nth, 2);
shout([Head | Tail], Survivors, Nth, Nth) ->
    %% io:format("~w survives~n",[Head]),
    shout(Tail, [Head | Survivors], Nth, 1);
shout([Head | Tail], Survivors, Nth, Counter) ->
    %% io:format("~w survives~n",[Head]),
    shout(Tail, [Head | Survivors], Nth, Counter + 1).

iter(0) -> ok;
iter(N) ->
    Result = shout(40,3),
    %% io:format("~w ~w~n",[N,Result]),
    iter(N-1).

benchmark() ->
    Iter = 1000000,
    iter(Iter),
    Start = os:timestamp(),
    iter(Iter),
    End = os:timestamp(),
    io:format("Time is ~w microseconds per iteration (element recursive)~n",[timer:now_diff(End,Start) / Iter]).
