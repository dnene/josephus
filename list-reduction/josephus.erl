-module(josephus).
-export([benchmark/0,shout/2]).

shout(Count,Nth) ->
    shoutList(lists:seq(1,Count),Nth,1).

shoutList([Head|[]],_,_) -> Head;
shoutList(List,N,Counter) ->
    {FilteredList, NextCounter} = shoutSubList(List,N,Counter,[]),
    shoutList(FilteredList,N, NextCounter).

shoutSubList([],_,Counter,Acc) -> {lists:reverse(Acc),Counter};
shoutSubList([_Head|Tail], N, 1, Acc) -> shoutSubList(Tail, N, 2, Acc);
shoutSubList([Head|Tail], N, Counter,Acc) ->
    NextCounter = 
        if
            Counter =:= N -> 1;
            true -> Counter + 1
        end,
    shoutSubList(Tail,N, NextCounter, [Head | Acc]).


iter(0) -> ok;
iter(N) ->
    Result = shout(40,3),
    %% io:format("~w ~w~n",[N,Result]),
    iter(N-1).

benchmark() ->
    Iter = 1000000,
    iter(Iter),
    Start = now(),
    iter(Iter),
    End = now(),
    io:format("Time is ~w microseconds per iteration (list reduction)~n",[timer:now_diff(End,Start) / Iter]).
