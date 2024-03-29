local key = KEYS[1];
local threadId = ARGV[1];
local time = ARGV[2];

-- key不存在
if(redis.call('exists', key) == 0) then
    redis.call('hset', key, threadId, '1');
    redis.call('expire', key, time);
    return 1;
end;

-- 当前线程已id存在
if(redis.call('hexists', key, threadId) == 1) then
    redis.call('hincrby', key, threadId, '1');
    redis.call('expire', key, time);
    return 1;
end;
return 0;